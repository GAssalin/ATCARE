package br.com.atcare.ms_pessoa.controller;

import br.com.atcare.ms_pessoa.model.dto.response.PessoaResumoResponse;
import br.com.atcare.ms_pessoa.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/pessoas")
@RequiredArgsConstructor
@Tag(name = "Pessoas", description = "Consulta consolidada de pessoas físicas e jurídicas")
public class PessoaController {
    private final PessoaService service;

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pessoa por ID")
    public ResponseEntity<PessoaResumoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarResumoPorId(id));
    }

    @GetMapping
    @Operation(summary = "Listar pessoas ativas")
    public ResponseEntity<List<PessoaResumoResponse>> listar(@RequestParam(required = false) String nome, Pageable pageable) {
        return ResponseEntity.ok(service.listarResumos(nome, pageable));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Desativar pessoa")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
