package br.com.atcare.ms_pessoa.controller;

import br.com.atcare.ms_pessoa.model.entity.PessoaJuridica;
import br.com.atcare.ms_pessoa.service.PessoaJuridicaService;
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
@RequestMapping("/v1/pj")
@RequiredArgsConstructor
@Tag(name = "PessoaJuridica", description = "Endpoints para gerenciamento de pessoas jurídicas")
public class PessoaJuridicaController {

    private final PessoaJuridicaService service;

    @PostMapping
    @Operation(summary = "Cadastrar pessoa")
    @ApiResponse(responseCode = "200", description = "PessoaJuridica cadastrada com sucesso",
            content = @Content(schema = @Schema(implementation = PessoaJuridica.class)))
    public ResponseEntity<PessoaJuridica> salvar(@RequestBody @Valid PessoaJuridica request) {
        return ResponseEntity.ok(service.salvar(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar pessoa")
    @ApiResponse(responseCode = "200", description = "PessoaJuridica atualizada com sucesso",
            content = @Content(schema = @Schema(implementation = PessoaJuridica.class)))
    public ResponseEntity<PessoaJuridica> atualizar(@PathVariable Long id, @RequestBody @Valid PessoaJuridica request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pessoa por ID")
    @ApiResponse(responseCode = "200", description = "PessoaJuridica encontrada",
            content = @Content(schema = @Schema(implementation = PessoaJuridica.class)))
    public ResponseEntity<PessoaJuridica> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping
    @Operation(summary = "Listar pessoas")
    @ApiResponse(responseCode = "200", description = "PessoaJuridicas listadas com sucesso",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = PessoaJuridica.class))))
    public ResponseEntity<List<PessoaJuridica>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir pessoa")
    @ApiResponse(responseCode = "204", description = "PessoaJuridica excluída com sucesso")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}