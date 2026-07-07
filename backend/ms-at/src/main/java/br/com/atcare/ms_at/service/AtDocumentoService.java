package br.com.atcare.ms_at.service;

import br.com.atcare.ms_at.model.entity.AtDocumento;
import br.com.atcare.ms_at.repository.AtDocumentoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AtDocumentoService {

    private final AtDocumentoRepository repository;

    public AtDocumento criar(AtDocumento entity) {
        return repository.save(entity);
    }

    public List<AtDocumento> listarTodos() {
        return repository.findAll();
    }

    public AtDocumento buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Documento não encontrado. Id: " + id));
    }

    public AtDocumento atualizar(Long id, AtDocumento entity) {
        AtDocumento existente = buscarPorId(id);

        existente.setAt(entity.getAt());
        existente.setNome(entity.getNome());
        existente.setTipo(entity.getTipo());
        existente.setArquivo(entity.getArquivo());
        existente.setObservacao(entity.getObservacao());

        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.delete(buscarPorId(id));
    }

    public void ativar(Long id) {
        AtDocumento entity = buscarPorId(id);
        entity.setAtivo(true);
        repository.save(entity);
    }

    public void inativar(Long id) {
        AtDocumento entity = buscarPorId(id);
        entity.setAtivo(false);
        repository.save(entity);
    }
}