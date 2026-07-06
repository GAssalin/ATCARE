package br.com.atcare.ms_pessoa.service;

import br.com.atcare.ms_pessoa.model.entity.Pessoa;
import br.com.atcare.ms_pessoa.model.entity.PessoaFisica;
import br.com.atcare.ms_pessoa.repository.PessoaFisicaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaFisicaService {

    private final PessoaFisicaRepository repository;

    @Transactional
    public PessoaFisica salvar(PessoaFisica entity) {
        return repository.save(entity);
    }

    @Transactional
    public PessoaFisica atualizar(Long id, Pessoa entity) {
        PessoaFisica existente = buscarPorId(id);
        BeanUtils.copyProperties(entity, existente, "id");
        return repository.save(existente);
    }

    @Transactional(readOnly = true)
    public PessoaFisica buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa Física não encontrada com id: " + id));
    }

    @Transactional(readOnly = true)
    public List<PessoaFisica> listar() {
        return repository.findAll();
    }

    @Transactional
    public void deletar(Long id) {
        PessoaFisica existente = buscarPorId(id);
        repository.delete(existente);
    }
}