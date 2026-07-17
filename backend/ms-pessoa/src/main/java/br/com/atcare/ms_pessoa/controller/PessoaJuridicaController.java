package br.com.atcare.ms_pessoa.controller;

import br.com.atcare.ms_pessoa.model.dto.request.PessoaJuridicaRequest;
import br.com.atcare.ms_pessoa.model.dto.response.PessoaJuridicaResponse;
import br.com.atcare.ms_pessoa.service.PessoaJuridicaService;
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
@RequestMapping("/v1/pessoas-juridicas")
@RequiredArgsConstructor
@Tag(name = "Pessoas jurídicas", description = "Gerenciamento de pessoas jurídicas")
public class PessoaJuridicaController {
    private final PessoaJuridicaService service;

    @PostMapping
    @Operation(summary = "Cadastrar pessoa jurídica")
    public ResponseEntity<PessoaJuridicaResponse> salvar(@RequestBody @Valid PessoaJuridicaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar pessoa jurídica")
    public ResponseEntity<PessoaJuridicaResponse> atualizar(@PathVariable Long id, @RequestBody @Valid PessoaJuridicaRequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pessoa jurídica por ID")
    public ResponseEntity<PessoaJuridicaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarResponsePorId(id));
    }

    @GetMapping
    @Operation(summary = "Listar pessoas jurídicas")
    public ResponseEntity<Page<PessoaJuridicaResponse>> listar(@RequestParam(required = false) String razaoSocial, Pageable pageable) {
        return ResponseEntity.ok(service.listar(razaoSocial, pageable));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Desativar pessoa jurídica")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
