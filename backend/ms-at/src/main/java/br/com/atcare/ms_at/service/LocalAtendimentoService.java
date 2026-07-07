package br.com.atcare.ms_at.service;

import br.com.atcare.ms_at.model.entity.LocalAtendimento;
import br.com.atcare.ms_at.repository.LocalAtendimentoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocalAtendimentoService {

    private final LocalAtendimentoRepository repository;

    public LocalAtendimento criar(LocalAtendimento entity) {
        return repository.save(entity);
    }

    public List<LocalAtendimento> listarTodos() {
        return repository.findAll();
    }

    public LocalAtendimento buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Local de atendimento não encontrado. Id: " + id));
    }

    public LocalAtendimento atualizar(Long id, LocalAtendimento entity) {
        LocalAtendimento existente = buscarPorId(id);

        existente.setAt(entity.getAt());
        existente.setEndereco(entity.getEndereco());
        existente.setAtendimentoDomiciliar(entity.getAtendimentoDomiciliar());
        existente.setAtendimentoOnline(entity.getAtendimentoOnline());

        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.delete(buscarPorId(id));
    }

    public void ativar(Long id) {
        LocalAtendimento entity = buscarPorId(id);
        entity.setAtivo(true);
        repository.save(entity);
    }

    public void inativar(Long id) {
        LocalAtendimento entity = buscarPorId(id);
        entity.setAtivo(false);
        repository.save(entity);
    }
}