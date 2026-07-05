package br.com.atcare.ms_pessoa.controller.internal;

import br.com.atcare.core.usuario.auth.dto.UsuarioAuthResponse;
import br.com.atcare.ms_pessoa.service.UsuarioService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@RestController
@RequestMapping("/internal")
@RequiredArgsConstructor
public class UsuarioInternalController {

    private final UsuarioService usuarioService;

    @GetMapping("/auth/by-email")
    public UsuarioAuthResponse buscarPorEmail(@RequestParam String email) {
        return usuarioService.buscarParaAutenticacao(email);
    }

}
