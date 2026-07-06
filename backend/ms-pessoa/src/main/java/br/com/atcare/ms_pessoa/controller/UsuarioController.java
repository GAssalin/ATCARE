package br.com.atcare.ms_pessoa.controller;

import br.com.atcare.ms_pessoa.model.entity.Usuario;
import br.com.atcare.ms_pessoa.service.UsuarioService;
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
@RequestMapping("/v1/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuarios", description = "Endpoints para gerenciamento de usuarios")
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping
    @Operation(summary = "Cadastrar pessoa")
    @ApiResponse(responseCode = "200", description = "Usuario cadastrada com sucesso",
            content = @Content(schema = @Schema(implementation = Usuario.class)))
    public ResponseEntity<Usuario> salvar(@RequestBody @Valid Usuario request) {
        return ResponseEntity.ok(service.salvar(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar pessoa")
    @ApiResponse(responseCode = "200", description = "Usuario atualizada com sucesso",
            content = @Content(schema = @Schema(implementation = Usuario.class)))
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody @Valid Usuario request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pessoa por ID")
    @ApiResponse(responseCode = "200", description = "Usuario encontrada",
            content = @Content(schema = @Schema(implementation = Usuario.class)))
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping
    @Operation(summary = "Listar pessoas")
    @ApiResponse(responseCode = "200", description = "Usuarios listadas com sucesso",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Usuario.class))))
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir pessoa")
    @ApiResponse(responseCode = "204", description = "Usuario excluída com sucesso")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}