package br.com.atcare.ms_autenticacao.client;

import br.com.atcare.core.usuario.auth.dto.UsuarioAuthResponse;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
@RequiredArgsConstructor
public class PessoaService {
    private final PessoaClient pessoaClient;

    @CircuitBreaker(name = "ms-pessoa", fallbackMethod = "fallbackBuscarUsuarioPorEmail")
    public UsuarioAuthResponse buscarUsuarioPorEmail(String email) {
        return pessoaClient.buscarPorEmail(email);
    }
    private UsuarioAuthResponse fallbackBuscarUsuarioPorEmail(String email, Throwable ex) {
        if (ex instanceof FeignException.NotFound) {
            log.warn("Tentativa de login para usuário não encontrado");
            throw new BadCredentialsException("E-mail ou senha inválidos");
        }
        log.error(
                "Fallback do CircuitBreaker acionado na busca do usuario pelo email [{}]. Causa: {}",
                email,
                ex.getMessage(),
                ex
        );

        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Não foi possível realizar o login no momento. Tente novamente em alguns instantes.");
    }
}
