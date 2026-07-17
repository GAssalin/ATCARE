package br.com.atcare.ms_pessoa.service;

import br.com.atcare.ms_pessoa.mapper.TipoRelacaoPessoaMapper;
import br.com.atcare.ms_pessoa.model.dto.request.TipoRelacaoPessoaRequest;
import br.com.atcare.ms_pessoa.model.dto.response.TipoRelacaoPessoaResponse;
import br.com.atcare.ms_pessoa.model.entity.TipoRelacaoPessoa;
import br.com.atcare.ms_pessoa.repository.TipoRelacaoPessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoRelacaoPessoaService {
    private final TipoRelacaoPessoaRepository repository;
    private final TipoRelacaoPessoaMapper mapper;

    @Transactional
    public TipoRelacaoPessoaResponse salvar(TipoRelacaoPessoaRequest r) {
        validarNome(r.nome(), null);
        return mapper.toResponse(repository.save(mapper.toEntity(r)));
    }

    @Transactional
    public TipoRelacaoPessoaResponse atualizar(Long id, TipoRelacaoPessoaRequest r) {
        TipoRelacaoPessoa e = buscarEntidade(id);
        validarNome(r.nome(), e);
        mapper.updateEntity(e, r);
        return mapper.toResponse(repository.save(e));
    }

    @Transactional(readOnly = true)
    public TipoRelacaoPessoaResponse buscarPorId(Long id) {
        return mapper.toResponse(buscarEntidade(id));
    }

    @Transactional(readOnly = true)
    public List<TipoRelacaoPessoaResponse> listar() {
        return repository.findAllByAtivoTrueOrderByNomeAsc().stream().map(mapper::toResponse).toList();
    }

    @Transactional
    public void deletar(Long id) {
        TipoRelacaoPessoa e = buscarEntidade(id);
        e.setAtivo(false);
        repository.save(e);
    }

    TipoRelacaoPessoa buscarEntidade(Long id) {
        return repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new EntityNotFoundException("Tipo de relação não encontrado: " + id));
    }

    private void validarNome(String nome, TipoRelacaoPessoa atual) {
        repository.findByNomeIgnoreCaseAndAtivoTrue(nome).filter(e -> atual == null || !e.getId().equals(atual.getId())).ifPresent(e -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Tipo de relação já cadastrado");
        });
    }
}
