package br.com.atcare.ms_pessoa.controller;

import br.com.atcare.ms_pessoa.model.dto.request.TipoRelacaoPessoaRequest;
import br.com.atcare.ms_pessoa.model.dto.response.TipoRelacaoPessoaResponse;
import br.com.atcare.ms_pessoa.service.TipoRelacaoPessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/tipos-relacao")
@RequiredArgsConstructor
@Tag(name = "Tipos de relação", description = "Gerenciamento dos tipos de vínculo entre pessoas")
public class TipoRelacaoPessoaController {
    private final TipoRelacaoPessoaService service;

    @PostMapping
    @Operation(summary = "Cadastrar tipo de relação")
    public ResponseEntity<TipoRelacaoPessoaResponse> salvar(@RequestBody @Valid TipoRelacaoPessoaRequest r) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(r));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar tipo de relação")
    public ResponseEntity<TipoRelacaoPessoaResponse> atualizar(@PathVariable Long id, @RequestBody @Valid TipoRelacaoPessoaRequest r) {
        return ResponseEntity.ok(service.atualizar(id, r));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar tipo de relação por ID")
    public ResponseEntity<TipoRelacaoPessoaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping
    @Operation(summary = "Listar tipos de relação")
    public ResponseEntity<List<TipoRelacaoPessoaResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Desativar tipo de relação")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
