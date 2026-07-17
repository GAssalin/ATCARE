package br.com.atcare.ms_pessoa.controller;

import br.com.atcare.ms_pessoa.model.dto.request.PessoaRelacaoRequest;
import br.com.atcare.ms_pessoa.model.dto.response.PessoaRelacaoResponse;
import br.com.atcare.ms_pessoa.service.PessoaRelacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/relacoes-pessoas")
@RequiredArgsConstructor
@Tag(name = "Relações entre pessoas", description = "Gerenciamento dos vínculos entre pessoas")
public class PessoaRelacaoController {
    private final PessoaRelacaoService service;

    @PostMapping
    @Operation(summary = "Cadastrar relação")
    public ResponseEntity<PessoaRelacaoResponse> salvar(@RequestBody @Valid PessoaRelacaoRequest r) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(r));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar relação")
    public ResponseEntity<PessoaRelacaoResponse> atualizar(@PathVariable Long id, @RequestBody @Valid PessoaRelacaoRequest r) {
        return ResponseEntity.ok(service.atualizar(id, r));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar relação por ID")
    public ResponseEntity<PessoaRelacaoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping
    @Operation(summary = "Listar relações de uma pessoa")
    public ResponseEntity<List<PessoaRelacaoResponse>> listar(@RequestParam Long pessoaId) {
        return ResponseEntity.ok(service.listarPorPessoa(pessoaId));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Desativar relação")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
