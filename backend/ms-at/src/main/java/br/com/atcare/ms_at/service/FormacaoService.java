package br.com.atcare.ms_at.service;

import br.com.atcare.ms_at.model.entity.Formacao;
import br.com.atcare.ms_at.repository.FormacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FormacaoService {

    private final FormacaoRepository repository;

    public Formacao criar(Formacao entity) {
        return repository.save(entity);
    }

    public List<Formacao> listarTodos() {
        return repository.findAll();
    }

    public Formacao buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Formação não encontrada. Id: " + id));
    }

    public Formacao atualizar(Long id, Formacao entity) {
        Formacao existente = buscarPorId(id);

        existente.setAt(entity.getAt());
        existente.setInstituicao(entity.getInstituicao());
        existente.setTitulo(entity.getTitulo());
        existente.setAnoConclusao(entity.getAnoConclusao());
        existente.setCursos(entity.getCursos());

        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.delete(buscarPorId(id));
    }

    public void ativar(Long id) {
        Formacao entity = buscarPorId(id);
        entity.setAtivo(true);
        repository.save(entity);
    }

    public void inativar(Long id) {
        Formacao entity = buscarPorId(id);
        entity.setAtivo(false);
        repository.save(entity);
    }
}