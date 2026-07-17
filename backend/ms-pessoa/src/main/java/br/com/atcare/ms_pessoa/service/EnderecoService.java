package br.com.atcare.ms_pessoa.service;

import br.com.atcare.ms_pessoa.mapper.EnderecoMapper;
import br.com.atcare.ms_pessoa.model.dto.request.EnderecoRequest;
import br.com.atcare.ms_pessoa.model.dto.response.EnderecoResponse;
import br.com.atcare.ms_pessoa.model.entity.Endereco;
import br.com.atcare.ms_pessoa.repository.EnderecoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnderecoService {
    private final EnderecoRepository repository;
    private final PessoaService pessoaService;
    private final MunicipioService municipioService;
    private final EnderecoMapper mapper;

    @Transactional
    public EnderecoResponse salvar(EnderecoRequest r) {
        if (r.principal()) removerPrincipalAtual(r.pessoaId(), null);
        return mapper.toResponse(repository.save(mapper.toEntity(r, pessoaService.buscarPorId(r.pessoaId()), municipioService.buscarEntidade(r.municipioId()))));
    }

    @Transactional
    public EnderecoResponse atualizar(Long id, EnderecoRequest r) {
        Endereco e = buscarEntidade(id);
        if (r.principal()) removerPrincipalAtual(r.pessoaId(), id);
        mapper.updateEntity(e, r, pessoaService.buscarPorId(r.pessoaId()), municipioService.buscarEntidade(r.municipioId()));
        return mapper.toResponse(repository.save(e));
    }

    @Transactional(readOnly = true)
    public EnderecoResponse buscarPorId(Long id) {
        return mapper.toResponse(buscarEntidade(id));
    }

    @Transactional(readOnly = true)
    public List<EnderecoResponse> listarPorPessoa(Long pessoaId) {
        pessoaService.buscarPorId(pessoaId);
        return repository.findAllByPessoaIdAndAtivoTrue(pessoaId).stream().map(mapper::toResponse).toList();
    }

    @Transactional
    public void deletar(Long id) {
        Endereco e = buscarEntidade(id);
        e.setAtivo(false);
        repository.save(e);
    }

    private Endereco buscarEntidade(Long id) {
        return repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado: " + id));
    }

    private void removerPrincipalAtual(Long pessoaId, Long ignorado) {
        repository.findFirstByPessoaIdAndPrincipalTrueAndAtivoTrue(pessoaId).filter(e -> !e.getId().equals(ignorado)).ifPresent(e -> {
            e.setPrincipal(false);
            repository.save(e);
        });
    }
}
