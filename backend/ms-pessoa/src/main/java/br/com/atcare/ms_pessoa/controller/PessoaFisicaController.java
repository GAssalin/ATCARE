package br.com.atcare.ms_pessoa.controller;

import br.com.atcare.ms_pessoa.model.entity.PessoaFisica;
import br.com.atcare.ms_pessoa.service.PessoaFisicaService;
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
@RequestMapping("/v1/pf")
@RequiredArgsConstructor
@Tag(name = "PessoaFisica", description = "Endpoints para gerenciamento de pessoas físicas")
public class PessoaFisicaController {

    private final PessoaFisicaService service;

    @PostMapping
    @Operation(summary = "Cadastrar pessoa")
    @ApiResponse(responseCode = "200", description = "PessoaFisica cadastrada com sucesso",
            content = @Content(schema = @Schema(implementation = PessoaFisica.class)))
    public ResponseEntity<PessoaFisica> salvar(@RequestBody @Valid PessoaFisica request) {
        return ResponseEntity.ok(service.salvar(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar pessoa")
    @ApiResponse(responseCode = "200", description = "PessoaFisica atualizada com sucesso",
            content = @Content(schema = @Schema(implementation = PessoaFisica.class)))
    public ResponseEntity<PessoaFisica> atualizar(@PathVariable Long id, @RequestBody @Valid PessoaFisica request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pessoa por ID")
    @ApiResponse(responseCode = "200", description = "PessoaFisica encontrada",
            content = @Content(schema = @Schema(implementation = PessoaFisica.class)))
    public ResponseEntity<PessoaFisica> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping
    @Operation(summary = "Listar pessoas")
    @ApiResponse(responseCode = "200", description = "PessoaFisicas listadas com sucesso",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = PessoaFisica.class))))
    public ResponseEntity<List<PessoaFisica>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir pessoa")
    @ApiResponse(responseCode = "204", description = "PessoaFisica excluída com sucesso")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}