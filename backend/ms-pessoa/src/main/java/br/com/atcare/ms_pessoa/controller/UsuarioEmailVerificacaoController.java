package br.com.atcare.ms_pessoa.controller;

import br.com.atcare.ms_pessoa.model.entity.UsuarioEmailVerificacao;
import br.com.atcare.ms_pessoa.service.UsuarioEmailVerificacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/email-verificacao")
@RequiredArgsConstructor
@Tag(name = "UsuarioEmailVerificacao", description = "Endpoints para gerenciamento de emails de verificação")
public class UsuarioEmailVerificacaoController {

    private final UsuarioEmailVerificacaoService service;

    @PostMapping
    @Operation(summary = "Cadastrar pessoa")
    @ApiResponse(responseCode = "200", description = "Email Verificacao cadastrada com sucesso",
            content = @Content(schema = @Schema(implementation = UsuarioEmailVerificacao.class)))
    public ResponseEntity<UsuarioEmailVerificacao> salvar(@RequestBody @Valid UsuarioEmailVerificacao request) {
        return ResponseEntity.ok(service.salvar(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar pessoa")
    @ApiResponse(responseCode = "200", description = "Email Verificacao atualizada com sucesso",
            content = @Content(schema = @Schema(implementation = UsuarioEmailVerificacao.class)))
    public ResponseEntity<UsuarioEmailVerificacao> atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioEmailVerificacao request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pessoa por ID")
    @ApiResponse(responseCode = "200", description = "Email Verificacao encontrada",
            content = @Content(schema = @Schema(implementation = UsuarioEmailVerificacao.class)))
    public ResponseEntity<UsuarioEmailVerificacao> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping
    @Operation(summary = "Listar pessoas")
    @ApiResponse(responseCode = "200", description = "Email Verificacao listadas com sucesso",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = UsuarioEmailVerificacao.class))))
    public ResponseEntity<List<UsuarioEmailVerificacao>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir pessoa")
    @ApiResponse(responseCode = "204", description = "Email Verificacao excluída com sucesso")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}