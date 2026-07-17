package br.com.atcare.ms_pessoa.controller;

import br.com.atcare.ms_pessoa.model.dto.request.PessoaFisicaRequest;
import br.com.atcare.ms_pessoa.model.dto.response.PessoaFisicaResponse;
import br.com.atcare.ms_pessoa.service.PessoaFisicaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/pessoas-fisicas")
@RequiredArgsConstructor
@Tag(name = "Pessoas físicas", description = "Gerenciamento de pessoas físicas")
public class PessoaFisicaController {
    private final PessoaFisicaService service;

    @PostMapping
    @Operation(summary = "Cadastrar pessoa física")
    public ResponseEntity<PessoaFisicaResponse> salvar(@RequestBody @Valid PessoaFisicaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar pessoa física")
    public ResponseEntity<PessoaFisicaResponse> atualizar(@PathVariable Long id, @RequestBody @Valid PessoaFisicaRequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pessoa física por ID")
    public ResponseEntity<PessoaFisicaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarResponsePorId(id));
    }

    @GetMapping
    @Operation(summary = "Listar pessoas físicas")
    public ResponseEntity<Page<PessoaFisicaResponse>> listar(@RequestParam(required = false) String nome, Pageable pageable) {
        return ResponseEntity.ok(service.listar(nome, pageable));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Desativar pessoa física")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
