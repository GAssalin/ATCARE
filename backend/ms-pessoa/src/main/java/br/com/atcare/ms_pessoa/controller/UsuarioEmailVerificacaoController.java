package br.com.atcare.ms_pessoa.controller;

import br.com.atcare.ms_pessoa.model.dto.request.UsuarioEmailVerificacaoRequest;
import br.com.atcare.ms_pessoa.model.dto.response.UsuarioEmailVerificacaoResponse;
import br.com.atcare.ms_pessoa.service.UsuarioEmailVerificacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/verificacoes-email")
@RequiredArgsConstructor
@Tag(name = "Verificação de e-mail", description = "Emissão e confirmação de tokens de e-mail")
public class UsuarioEmailVerificacaoController {
    private final UsuarioEmailVerificacaoService service;

    @PostMapping("/usuarios/{usuarioId}")
    @Operation(summary = "Criar verificação de e-mail")
    public ResponseEntity<UsuarioEmailVerificacaoResponse> criar(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioEmailVerificacaoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(usuarioId, request.token(), LocalDateTime.now().plusHours(24)));
    }

    @PostMapping("/confirmacao")
    @Operation(summary = "Confirmar e-mail")
    public ResponseEntity<UsuarioEmailVerificacaoResponse> confirmar(@RequestBody @Valid UsuarioEmailVerificacaoRequest request) {
        return ResponseEntity.ok(service.confirmar(request.token()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar verificação por ID")
    public ResponseEntity<UsuarioEmailVerificacaoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarResponsePorId(id));
    }

    @GetMapping
    @Operation(summary = "Listar verificações ativas")
    public ResponseEntity<List<UsuarioEmailVerificacaoResponse>> listar() {
        return null;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Desativar verificação")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
