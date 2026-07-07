package br.com.atcare.ms_at.service;

import br.com.atcare.ms_at.model.entity.Curso;
import br.com.atcare.ms_at.repository.CursoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository repository;

    public Curso criar(Curso entity) {
        return repository.save(entity);
    }

    public List<Curso> listarTodos() {
        return repository.findAll();
    }

    public Curso buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado. Id: " + id));
    }

    public Curso atualizar(Long id, Curso entity) {
        Curso existente = buscarPorId(id);

        existente.setFormacao(entity.getFormacao());
        existente.setNome(entity.getNome());
        existente.setInstituicao(entity.getInstituicao());
        existente.setCargaHoraria(entity.getCargaHoraria());
        existente.setCertificados(entity.getCertificados());

        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.delete(buscarPorId(id));
    }

    public void ativar(Long id) {
        Curso entity = buscarPorId(id);
        entity.setAtivo(true);
        repository.save(entity);
    }

    public void inativar(Long id) {
        Curso entity = buscarPorId(id);
        entity.setAtivo(false);
        repository.save(entity);
    }
}