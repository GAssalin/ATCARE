package br.com.atcare.ms_at.service;

import br.com.atcare.ms_at.model.entity.AtEspecialidade;
import br.com.atcare.ms_at.repository.AtEspecialidadeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AtEspecialidadeService {

    private final AtEspecialidadeRepository repository;

    public AtEspecialidade criar(AtEspecialidade entity) {
        return repository.save(entity);
    }

    public List<AtEspecialidade> listarTodos() {
        return repository.findAll();
    }

    public AtEspecialidade buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Especialidade do AT não encontrada. Id: " + id));
    }

    public AtEspecialidade atualizar(Long id, AtEspecialidade entity) {
        AtEspecialidade existente = buscarPorId(id);

        existente.setAt(entity.getAt());
        existente.setEspecialidade(entity.getEspecialidade());

        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.delete(buscarPorId(id));
    }

    public void ativar(Long id) {
        AtEspecialidade entity = buscarPorId(id);
        entity.setAtivo(true);
        repository.save(entity);
    }

    public void inativar(Long id) {
        AtEspecialidade entity = buscarPorId(id);
        entity.setAtivo(false);
        repository.save(entity);
    }
}