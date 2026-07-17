package br.com.atcare.ms_pessoa.service;

import br.com.atcare.ms_pessoa.mapper.ContatoMapper;
import br.com.atcare.ms_pessoa.model.dto.request.ContatoRequest;
import br.com.atcare.ms_pessoa.model.dto.response.ContatoResponse;
import br.com.atcare.ms_pessoa.model.entity.Contato;
import br.com.atcare.ms_pessoa.model.entity.Pessoa;
import br.com.atcare.ms_pessoa.repository.ContatoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContatoService {
    private final ContatoRepository repository;
    private final PessoaService pessoaService;
    private final ContatoMapper mapper;

    @Transactional
    public ContatoResponse salvar(ContatoRequest request) {
        Pessoa pessoa = pessoaService.buscarPorId(request.pessoaId());
        validarDuplicidade(request, null);
        if (request.principal()) removerPrincipalAtual(request.pessoaId(), null);
        return mapper.toResponse(repository.save(mapper.toEntity(request, pessoa)));
    }

    @Transactional
    public ContatoResponse atualizar(Long id, ContatoRequest request) {
        Contato entity = buscarEntidade(id);
        Pessoa pessoa = pessoaService.buscarPorId(request.pessoaId());
        validarDuplicidade(request, entity);
        if (request.principal()) removerPrincipalAtual(request.pessoaId(), id);
        mapper.updateEntity(entity, request, pessoa);
        return mapper.toResponse(repository.save(entity));
    }

    @Transactional(readOnly = true)
    public ContatoResponse buscarPorId(Long id) {
        return mapper.toResponse(buscarEntidade(id));
    }

    @Transactional(readOnly = true)
    public List<ContatoResponse> listarPorPessoa(Long pessoaId) {
        pessoaService.buscarPorId(pessoaId);
        return repository.findAllByPessoaIdAndAtivoTrue(pessoaId).stream().map(mapper::toResponse).toList();
    }

    @Transactional
    public void deletar(Long id) {
        Contato e = buscarEntidade(id);
        e.setAtivo(false);
        repository.save(e);
    }

    private Contato buscarEntidade(Long id) {
        return repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new EntityNotFoundException("Contato não encontrado: " + id));
    }

    private void validarDuplicidade(ContatoRequest r, Contato atual) {
        if (repository.existsByPessoaIdAndTipoAndValorAndAtivoTrue(r.pessoaId(), r.tipo(), r.valor())
                && (atual == null || !atual.getPessoa().getId().equals(r.pessoaId()) || atual.getTipo() != r.tipo() || !atual.getValor().equals(r.valor())))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Contato já cadastrado para a pessoa");
    }

    private void removerPrincipalAtual(Long pessoaId, Long idIgnorado) {
        repository.findFirstByPessoaIdAndPrincipalTrueAndAtivoTrue(pessoaId).filter(e -> !e.getId().equals(idIgnorado)).ifPresent(e -> {
            e.setPrincipal(false);
            repository.save(e);
        });
    }
}
