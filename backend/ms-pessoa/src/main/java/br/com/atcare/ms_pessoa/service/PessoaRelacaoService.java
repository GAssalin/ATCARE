package br.com.atcare.ms_pessoa.service;

import br.com.atcare.ms_pessoa.mapper.PessoaRelacaoMapper;
import br.com.atcare.ms_pessoa.model.dto.request.PessoaRelacaoRequest;
import br.com.atcare.ms_pessoa.model.dto.response.PessoaRelacaoResponse;
import br.com.atcare.ms_pessoa.model.entity.Pessoa;
import br.com.atcare.ms_pessoa.model.entity.PessoaRelacao;
import br.com.atcare.ms_pessoa.model.entity.TipoRelacaoPessoa;
import br.com.atcare.ms_pessoa.repository.PessoaRelacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaRelacaoService {
    private final PessoaRelacaoRepository repository;
    private final PessoaService pessoaService;
    private final TipoRelacaoPessoaService tipoService;
    private final PessoaRelacaoMapper mapper;

    @Transactional
    public PessoaRelacaoResponse salvar(PessoaRelacaoRequest r) {
        validar(r, null);
        Pessoa p = pessoaService.buscarPorId(r.pessoaId());
        Pessoa relacionado = pessoaService.buscarPorId(r.relacionadoId());
        TipoRelacaoPessoa tipo = tipoService.buscarEntidade(r.tipoRelacaoId());
        return mapper.toResponse(repository.save(mapper.toEntity(r, p, relacionado, tipo)));
    }

    @Transactional
    public PessoaRelacaoResponse atualizar(Long id, PessoaRelacaoRequest r) {
        PessoaRelacao e = buscarEntidade(id);
        validar(r, e);
        mapper.updateEntity(e, r, pessoaService.buscarPorId(r.pessoaId()), pessoaService.buscarPorId(r.relacionadoId()), tipoService.buscarEntidade(r.tipoRelacaoId()));
        return mapper.toResponse(repository.save(e));
    }

    @Transactional(readOnly = true)
    public PessoaRelacaoResponse buscarPorId(Long id) {
        return mapper.toResponse(buscarEntidade(id));
    }

    @Transactional(readOnly = true)
    public List<PessoaRelacaoResponse> listarPorPessoa(Long pessoaId) {
        pessoaService.buscarPorId(pessoaId);
        return repository.findAllByPessoaIdAndAtivoTrue(pessoaId).stream().map(mapper::toResponse).toList();
    }

    @Transactional
    public void deletar(Long id) {
        PessoaRelacao e = buscarEntidade(id);
        e.setAtivo(false);
        repository.save(e);
    }

    private PessoaRelacao buscarEntidade(Long id) {
        return repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new EntityNotFoundException("Relação entre pessoas não encontrada: " + id));
    }

    private void validar(PessoaRelacaoRequest r, PessoaRelacao atual) {
        if (r.pessoaId().equals(r.relacionadoId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Uma pessoa não pode se relacionar consigo mesma");
        if (repository.existsByPessoaIdAndRelacionadoIdAndTipoRelacaoIdAndAtivoTrue(r.pessoaId(), r.relacionadoId(), r.tipoRelacaoId()) && (atual == null || !mesmaRelacao(atual, r)))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Relação já cadastrada");
    }

    private boolean mesmaRelacao(PessoaRelacao e, PessoaRelacaoRequest r) {
        return e.getPessoa().getId().equals(r.pessoaId()) && e.getRelacionado().getId().equals(r.relacionadoId()) && e.getTipoRelacao().getId().equals(r.tipoRelacaoId());
    }
}
