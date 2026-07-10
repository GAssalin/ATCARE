package br.com.atcare.ms_pessoa.service;

import br.com.atcare.core.usuario.auth.dto.UsuarioAuthResponse;
import br.com.atcare.ms_pessoa.model.entity.Usuario;
import br.com.atcare.ms_pessoa.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    @Transactional
    public Usuario salvar(Usuario entity) {
        return repository.save(entity);
    }

    @Transactional
    public Usuario atualizar(Long id, Usuario entity) {
        Usuario existente = buscarPorId(id);
        BeanUtils.copyProperties(entity, existente, "id");
        return repository.save(existente);
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrada com id: " + id));
    }

    @Transactional(readOnly = true)
    public UsuarioAuthResponse buscarPorEmail(String email) {
        Usuario usuario = repository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrada com email: " + email));
        return new UsuarioAuthResponse(usuario.getId(), usuario.getEmail(), usuario.getPassword());
    }

    @Transactional(readOnly = true)
    public List<Usuario> listar() {
        return repository.findAll();
    }

    @Transactional
    public void deletar(Long id) {
        Usuario existente = buscarPorId(id);
        repository.delete(existente);
    }
}