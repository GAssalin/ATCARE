package br.com.atcare.ms_autenticacao.service;

import br.com.atcare.core.usuario.auth.dto.UsuarioAuthResponse;
import br.com.atcare.ms_autenticacao.client.PessoaClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
        log.error(
                "Fallback do CircuitBreaker acionado na busca do usuario pelo email [{}]. Causa: {}",
                email,
                ex.getMessage(),
                ex
        );

        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Serviço de busca do usuário temporariamente indisponível");
    }
}
