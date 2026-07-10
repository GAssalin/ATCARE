package br.com.atcare.ms_pessoa.service;

import br.com.atcare.ms_pessoa.model.entity.Pessoa;
import br.com.atcare.ms_pessoa.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PessoaService {

    private final PessoaRepository repository;

    @Transactional
    public Pessoa salvar(Pessoa entity) {
        return repository.save(entity);
    }

    @Transactional
    public Pessoa atualizar(Long id, Pessoa entity) {
        Pessoa existente = buscarPorId(id);
        BeanUtils.copyProperties(entity, existente, "id");
        return repository.save(existente);
    }

    @Transactional(readOnly = true)
    public Pessoa buscarPorId(Long id) {
        Optional<Pessoa> p = repository.findById(id);
        if(!p.isPresent()) {
            log.error("Pessoa não encontrada com id: " + id);
            throw new EntityNotFoundException("Pessoa não encontrada com id: " + id);
        } else {
            return p.get();
        }
    }

    @Transactional(readOnly = true)
    public List<Pessoa> listar() {
        return repository.findAll();
    }

    @Transactional
    public void deletar(Long id) {
        Pessoa existente = buscarPorId(id);
        repository.delete(existente);
    }
}