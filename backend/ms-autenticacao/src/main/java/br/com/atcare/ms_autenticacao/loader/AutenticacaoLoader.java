package br.com.atcare.ms_autenticacao.loader;

import br.com.atcare.core.usuario.auth.dto.UsuarioAuthResponse;
import br.com.atcare.core.auth.model.AuthenticatedUser;
import br.com.atcare.ms_autenticacao.client.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AutenticacaoLoader {
    private final PasswordEncoder passwordEncoder;
    private final PessoaService pessoaService;

    public AuthenticatedUser authenticate(String email, String senha) {
        final UsuarioAuthResponse user = pessoaService.buscarUsuarioPorEmail(email);

        if (user == null || !passwordEncoder.matches(senha, user.passwordHash()))
            throw new BadCredentialsException("Usuário ou senha inválidos");

        return new AuthenticatedUser(
                user.userId(),
                user.email(),
                user.passwordHash()
        );
    }
}