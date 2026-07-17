package br.com.atcare.ms_pessoa.controller;

import br.com.atcare.ms_pessoa.model.dto.request.EnderecoRequest;
import br.com.atcare.ms_pessoa.model.dto.response.EnderecoResponse;
import br.com.atcare.ms_pessoa.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/enderecos")
@RequiredArgsConstructor
@Tag(name = "Endereços", description = "Gerenciamento dos endereços das pessoas")
public class EnderecoController {
    private final EnderecoService service;

    @PostMapping
    @Operation(summary = "Cadastrar endereço")
    public ResponseEntity<EnderecoResponse> salvar(@RequestBody @Valid EnderecoRequest r) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(r));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar endereço")
    public ResponseEntity<EnderecoResponse> atualizar(@PathVariable Long id, @RequestBody @Valid EnderecoRequest r) {
        return ResponseEntity.ok(service.atualizar(id, r));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar endereço por ID")
    public ResponseEntity<EnderecoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping
    @Operation(summary = "Listar endereços de uma pessoa")
    public ResponseEntity<List<EnderecoResponse>> listar(@RequestParam Long pessoaId) {
        return ResponseEntity.ok(service.listarPorPessoa(pessoaId));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Desativar endereço")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
