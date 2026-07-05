package br.com.atcare.ms_pessoa.controller.internal;

import br.com.atcare.core.usuario.auth.dto.UsuarioAuthResponse;
import br.com.atcare.ms_pessoa.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/pessoas")
@RequiredArgsConstructor
public class PessoaInternalController {

    private final PessoaService pessoaService;

    @GetMapping("/buscarPorEmail")
    public UsuarioAuthResponse buscarPorEmail(@RequestParam String email) {
        return new UsuarioAuthResponse(
                1L,
                "teste@teste.com",
                "$2a$10$LAnWRm2Abm4HN0b.cYnUQeu7Tf5DbGeI6BzueIFsdJF6Bv7eb8vzq"
        );
    }
}
