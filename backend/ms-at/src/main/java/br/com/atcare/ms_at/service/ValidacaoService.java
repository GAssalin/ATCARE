package br.com.atcare.ms_at.service;

import br.com.atcare.ms_at.model.entity.Validacao;
import br.com.atcare.ms_at.repository.ValidacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ValidacaoService {

    private final ValidacaoRepository repository;

    public Validacao criar(Validacao entity) {
        return repository.save(entity);
    }

    public List<Validacao> listarTodos() {
        return repository.findAll();
    }

    public Validacao buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Validação não encontrada. Id: " + id));
    }

    public Validacao atualizar(Long id, Validacao entity) {
        Validacao existente = buscarPorId(id);

        existente.setCertificado(entity.getCertificado());
        existente.setAprovado(entity.getAprovado());
        existente.setObservacao(entity.getObservacao());
        existente.setDataValidacao(entity.getDataValidacao());
        existente.setUsuarioValidador(entity.getUsuarioValidador());

        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.delete(buscarPorId(id));
    }

    public void ativar(Long id) {
        Validacao entity = buscarPorId(id);
        entity.setAtivo(true);
        repository.save(entity);
    }

    public void inativar(Long id) {
        Validacao entity = buscarPorId(id);
        entity.setAtivo(false);
        repository.save(entity);
    }
}