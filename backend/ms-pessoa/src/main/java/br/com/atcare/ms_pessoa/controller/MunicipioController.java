package br.com.atcare.ms_pessoa.controller;

import br.com.atcare.ms_pessoa.model.dto.request.MunicipioRequest;
import br.com.atcare.ms_pessoa.model.dto.response.MunicipioResponse;
import br.com.atcare.ms_pessoa.model.enums.Uf;
import br.com.atcare.ms_pessoa.service.MunicipioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/municipios")
@RequiredArgsConstructor
@Tag(name = "Municípios", description = "Gerenciamento dos municípios")
public class MunicipioController {
    private final MunicipioService service;

    @PostMapping
    @Operation(summary = "Cadastrar município")
    public ResponseEntity<MunicipioResponse> salvar(@RequestBody @Valid MunicipioRequest r) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(r));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar município")
    public ResponseEntity<MunicipioResponse> atualizar(@PathVariable Long id, @RequestBody @Valid MunicipioRequest r) {
        return ResponseEntity.ok(service.atualizar(id, r));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar município por ID")
    public ResponseEntity<MunicipioResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping
    @Operation(summary = "Listar municípios por UF")
    public ResponseEntity<List<MunicipioResponse>> listar(@RequestParam Uf uf) {
        return ResponseEntity.ok(service.listarPorUf(uf));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Desativar município")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
