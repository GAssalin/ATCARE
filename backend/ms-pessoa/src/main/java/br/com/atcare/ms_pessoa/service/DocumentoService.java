package br.com.atcare.ms_pessoa.service;

import br.com.atcare.ms_pessoa.mapper.DocumentoMapper;
import br.com.atcare.ms_pessoa.model.dto.request.DocumentoRequest;
import br.com.atcare.ms_pessoa.model.dto.response.DocumentoResponse;
import br.com.atcare.ms_pessoa.model.entity.Documento;
import br.com.atcare.ms_pessoa.model.entity.Pessoa;
import br.com.atcare.ms_pessoa.repository.DocumentoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentoService {
    private final DocumentoRepository repository;
    private final PessoaService pessoaService;
    private final DocumentoMapper mapper;

    @Transactional
    public DocumentoResponse salvar(DocumentoRequest r) {
        validarNumero(r, null);
        Pessoa p = pessoaService.buscarPorId(r.pessoaId());
        return mapper.toResponse(repository.save(mapper.toEntity(r, p)));
    }

    @Transactional
    public DocumentoResponse atualizar(Long id, DocumentoRequest r) {
        Documento e = buscarEntidade(id);
        validarNumero(r, e);
        Pessoa p = pessoaService.buscarPorId(r.pessoaId());
        mapper.updateEntity(e, r, p);
        return mapper.toResponse(repository.save(e));
    }

    @Transactional(readOnly = true)
    public DocumentoResponse buscarPorId(Long id) {
        return mapper.toResponse(buscarEntidade(id));
    }

    @Transactional(readOnly = true)
    public List<DocumentoResponse> listarPorPessoa(Long pessoaId) {
        pessoaService.buscarPorId(pessoaId);
        return repository.findAllByPessoaIdAndAtivoTrue(pessoaId).stream().map(mapper::toResponse).toList();
    }

    @Transactional
    public void deletar(Long id) {
        Documento e = buscarEntidade(id);
        e.setAtivo(false);
        repository.save(e);
    }

    private Documento buscarEntidade(Long id) {
        return repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new EntityNotFoundException("Documento não encontrado: " + id));
    }

    private void validarNumero(DocumentoRequest r, Documento atual) {
        repository.findByTipoAndNumeroAndAtivoTrue(r.tipo(), r.numero()).filter(e -> atual == null || !e.getId().equals(atual.getId())).ifPresent(e -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Documento já cadastrado");
        });
    }
}
