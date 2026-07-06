package br.com.atcare.ms_pessoa.service;

import br.com.atcare.ms_pessoa.model.entity.UsuarioEmailVerificacao;
import br.com.atcare.ms_pessoa.repository.UsuarioEmailVerificacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioEmailVerificacaoService {

    private final UsuarioEmailVerificacaoRepository repository;

    @Transactional
    public UsuarioEmailVerificacao salvar(UsuarioEmailVerificacao entity) {
        return repository.save(entity);
    }

    @Transactional
    public UsuarioEmailVerificacao atualizar(Long id, UsuarioEmailVerificacao entity) {
        UsuarioEmailVerificacao existente = buscarPorId(id);
        BeanUtils.copyProperties(entity, existente, "id");
        return repository.save(existente);
    }

    @Transactional(readOnly = true)
    public UsuarioEmailVerificacao buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario Email Verificacao não encontrada com id: " + id));
    }

    @Transactional(readOnly = true)
    public List<UsuarioEmailVerificacao> listar() {
        return repository.findAll();
    }

    @Transactional
    public void deletar(Long id) {
        UsuarioEmailVerificacao existente = buscarPorId(id);
        repository.delete(existente);
    }
}