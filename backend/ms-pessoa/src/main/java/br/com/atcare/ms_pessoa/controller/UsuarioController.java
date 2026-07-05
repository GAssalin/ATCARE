package br.com.atcare.ms_pessoa.controller;

import br.com.atcare.ms_pessoa.model.dto.usuario.UsuarioRequest;
import br.com.atcare.ms_pessoa.model.dto.usuario.UsuarioResponse;
import br.com.atcare.ms_pessoa.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponse> salvar(@Valid @RequestBody UsuarioRequest request) {
        UsuarioResponse response = usuarioService.salvar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioRequest request
    ) {
        UsuarioResponse response = usuarioService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable Long id) {
        UsuarioResponse response = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/login/{login}")
    public ResponseEntity<UsuarioResponse> buscarPorLogin(@PathVariable String login) {
        UsuarioResponse response = usuarioService.buscarPorLogin(login);
        return ResponseEntity.ok(response);
    }

}