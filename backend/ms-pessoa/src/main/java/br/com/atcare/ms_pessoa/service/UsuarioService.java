package br.com.atcare.ms_pessoa.service;

import br.com.atcare.core.usuario.auth.dto.UsuarioAuthResponse;
import br.com.atcare.ms_pessoa.model.dto.usuario.UsuarioRequest;
import br.com.atcare.ms_pessoa.model.dto.usuario.UsuarioResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UsuarioService {

    @Transactional(readOnly = true)
    public UsuarioAuthResponse buscarParaAutenticacao(String email) {
        return new UsuarioAuthResponse(
                0L,
                "Usuario teste",
                "hash"
        );
    }

    @Transactional
    public UsuarioResponse salvar(UsuarioRequest request) {
        return new UsuarioResponse(
                0L,
                "Usuario salvo",
                0L,
                "email",
                true,
                true
        );
    }

    @Transactional
    public UsuarioResponse atualizar(Long id, UsuarioRequest request) {
        return new UsuarioResponse(
                0L,
                "Usuario atualizado",
                0L,
                "email",
                true,
                true
        );
    }

    @Transactional(readOnly = true)
    public UsuarioResponse buscarPorId(Long id) {
        return new UsuarioResponse(
                0L,
                "Usuario teste",
                0L,
                "email",
                true,
                true
        );
    }

    @Transactional(readOnly = true)
    public UsuarioResponse buscarPorLogin(String login) {
        return new UsuarioResponse(
                0L,
                "Usuario teste",
                0L,
                "email",
                true,
                true
        );
    }

}