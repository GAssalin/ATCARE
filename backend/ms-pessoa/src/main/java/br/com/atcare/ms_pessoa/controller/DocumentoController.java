package br.com.atcare.ms_pessoa.controller;

import br.com.atcare.ms_pessoa.model.dto.request.DocumentoRequest;
import br.com.atcare.ms_pessoa.model.dto.response.DocumentoResponse;
import br.com.atcare.ms_pessoa.service.DocumentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/documentos")
@RequiredArgsConstructor
@Tag(name = "Documentos", description = "Gerenciamento dos documentos das pessoas")
public class DocumentoController {
    private final DocumentoService service;

    @PostMapping
    @Operation(summary = "Cadastrar documento")
    public ResponseEntity<DocumentoResponse> salvar(@RequestBody @Valid DocumentoRequest r) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(r));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar documento")
    public ResponseEntity<DocumentoResponse> atualizar(@PathVariable Long id, @RequestBody @Valid DocumentoRequest r) {
        return ResponseEntity.ok(service.atualizar(id, r));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar documento por ID")
    public ResponseEntity<DocumentoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping
    @Operation(summary = "Listar documentos de uma pessoa")
    public ResponseEntity<List<DocumentoResponse>> listar(@RequestParam Long pessoaId) {
        return ResponseEntity.ok(service.listarPorPessoa(pessoaId));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Desativar documento")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
