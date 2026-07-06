package br.com.atcare.ms_pessoa.service;

import br.com.atcare.ms_pessoa.model.entity.PessoaJuridica;
import br.com.atcare.ms_pessoa.repository.PessoaJuridicaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaJuridicaService {

    private final PessoaJuridicaRepository repository;

    @Transactional
    public PessoaJuridica salvar(PessoaJuridica entity) {
        return repository.save(entity);
    }

    @Transactional
    public PessoaJuridica atualizar(Long id, PessoaJuridica entity) {
        PessoaJuridica existente = buscarPorId(id);
        BeanUtils.copyProperties(entity, existente, "id");
        return repository.save(existente);
    }

    @Transactional(readOnly = true)
    public PessoaJuridica buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa Juridica não encontrada com id: " + id));
    }

    @Transactional(readOnly = true)
    public List<PessoaJuridica> listar() {
        return repository.findAll();
    }

    @Transactional
    public void deletar(Long id) {
        PessoaJuridica existente = buscarPorId(id);
        repository.delete(existente);
    }
}