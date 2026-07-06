package br.com.atcare.ms_pessoa.controller;

import br.com.atcare.ms_pessoa.model.entity.Pessoa;
import br.com.atcare.ms_pessoa.service.PessoaService;
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
@RequestMapping("/v1/pessoas")
@RequiredArgsConstructor
@Tag(name = "Pessoas", description = "Endpoints para gerenciamento de pessoas")
public class PessoaController {

    private final PessoaService service;

    @PostMapping
    @Operation(summary = "Cadastrar pessoa")
    @ApiResponse(responseCode = "200", description = "Pessoa cadastrada com sucesso",
            content = @Content(schema = @Schema(implementation = Pessoa.class)))
    public ResponseEntity<Pessoa> salvar(@RequestBody @Valid Pessoa request) {
        return ResponseEntity.ok(service.salvar(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar pessoa")
    @ApiResponse(responseCode = "200", description = "Pessoa atualizada com sucesso",
            content = @Content(schema = @Schema(implementation = Pessoa.class)))
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @RequestBody @Valid Pessoa request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pessoa por ID")
    @ApiResponse(responseCode = "200", description = "Pessoa encontrada",
            content = @Content(schema = @Schema(implementation = Pessoa.class)))
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping
    @Operation(summary = "Listar pessoas")
    @ApiResponse(responseCode = "200", description = "Pessoas listadas com sucesso",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Pessoa.class))))
    public ResponseEntity<List<Pessoa>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir pessoa")
    @ApiResponse(responseCode = "204", description = "Pessoa excluída com sucesso")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}