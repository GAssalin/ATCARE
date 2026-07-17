package br.com.atcare.ms_pessoa.controller;

import br.com.atcare.ms_pessoa.model.dto.request.ContatoRequest;
import br.com.atcare.ms_pessoa.model.dto.response.ContatoResponse;
import br.com.atcare.ms_pessoa.service.ContatoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/contatos")
@RequiredArgsConstructor
@Tag(name = "Contatos", description = "Gerenciamento dos contatos das pessoas")
public class ContatoController {
    private final ContatoService service;

    @PostMapping
    @Operation(summary = "Cadastrar contato")
    public ResponseEntity<ContatoResponse> salvar(@RequestBody @Valid ContatoRequest r) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(r));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar contato")
    public ResponseEntity<ContatoResponse> atualizar(@PathVariable Long id, @RequestBody @Valid ContatoRequest r) {
        return ResponseEntity.ok(service.atualizar(id, r));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar contato por ID")
    public ResponseEntity<ContatoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping
    @Operation(summary = "Listar contatos de uma pessoa")
    public ResponseEntity<List<ContatoResponse>> listar(@RequestParam Long pessoaId) {
        return ResponseEntity.ok(service.listarPorPessoa(pessoaId));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Desativar contato")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
