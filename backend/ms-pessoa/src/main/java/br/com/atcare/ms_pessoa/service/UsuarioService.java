package br.com.atcare.ms_pessoa.service;

import br.com.atcare.core.usuario.auth.dto.UsuarioAuthResponse;
import br.com.atcare.ms_pessoa.mapper.UsuarioMapper;
import br.com.atcare.ms_pessoa.model.dto.request.UsuarioRequest;
import br.com.atcare.ms_pessoa.model.dto.response.UsuarioResponse;
import br.com.atcare.ms_pessoa.model.entity.Usuario;
import br.com.atcare.ms_pessoa.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UsuarioResponse salvar(UsuarioRequest r) {
        validarUnicidade(r.email(), r.pessoaId(), null);
        return mapper.toResponse(repository.save(mapper.toEntity(r, passwordEncoder.encode(r.password()))));
    }

    @Transactional
    public UsuarioResponse atualizar(Long id, UsuarioRequest r) {
        Usuario e = buscarPorId(id);
        validarUnicidade(r.email(), r.pessoaId(), e);
        mapper.updateEntity(e, r, passwordEncoder.encode(r.password()));
        return mapper.toResponse(repository.save(e));
    }

    @Transactional(readOnly = true)
    public UsuarioResponse buscarResponsePorId(Long id) {
        return mapper.toResponse(buscarPorId(id));
    }

    @Transactional(readOnly = true)
    public UsuarioAuthResponse buscarPorEmail(String email) {
        Usuario e = repository.findByEmailIgnoreCaseAndAtivoTrue(email).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado para o e-mail informado"));
        return new UsuarioAuthResponse(e.getId(), e.getEmail(), e.getPassword());
    }

    @Transactional
    public Usuario salvar(Usuario e) {
        validarUnicidade(e.getEmail(), e.getPessoaId(), null);
        e.setId(null);
        e.setPassword(passwordEncoder.encode(e.getPassword()));
        return repository.save(e);
    }

    @Transactional
    public Usuario atualizar(Long id, Usuario dados) {
        Usuario e = buscarPorId(id);
        validarUnicidade(dados.getEmail(), dados.getPessoaId(), e);
        e.setPessoaId(dados.getPessoaId());
        e.setEmail(dados.getEmail());
        if (dados.getPassword() != null && !dados.getPassword().isBlank())
            e.setPassword(passwordEncoder.encode(dados.getPassword()));
        return repository.save(e);
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado: " + id));
    }

    @Transactional(readOnly = true)
    public List<Usuario> listar() {
        return repository.findAll().stream().filter(Usuario::getAtivo).toList();
    }

    @Transactional
    public void deletar(Long id) {
        Usuario e = buscarPorId(id);
        e.setAtivo(false);
        repository.save(e);
    }

    @Transactional
    public void marcarEmailVerificado(Usuario usuario) {
        usuario.setEmailVerificado(true);
        repository.save(usuario);
    }

    private void validarUnicidade(String email, Long pessoaId, Usuario atual) {
        repository.findByEmailIgnoreCaseAndAtivoTrue(email).filter(e -> atual == null || !e.getId().equals(atual.getId())).ifPresent(e -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado");
        });
        if (pessoaId != null)
            repository.findByPessoaIdAndAtivoTrue(pessoaId).filter(e -> atual == null || !e.getId().equals(atual.getId())).ifPresent(e -> {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Pessoa já vinculada a outro usuário");
            });
    }

}
