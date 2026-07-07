package br.com.atcare.ms_at.controller;

import br.com.atcare.ms_at.model.entity.Validacao;
import br.com.atcare.ms_at.service.ValidacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/validacoes")
@RequiredArgsConstructor
public class ValidacaoController {

    private final ValidacaoService validacaoService;

    @PostMapping
    public ResponseEntity<Validacao> criar(@RequestBody Validacao entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(validacaoService.criar(entity));
    }

    @GetMapping
    public ResponseEntity<List<Validacao>> listarTodos() {
        return ResponseEntity.ok(validacaoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Validacao> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(validacaoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Validacao> atualizar(@PathVariable Long id, @RequestBody Validacao entity) {
        return ResponseEntity.ok(validacaoService.atualizar(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        validacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/ativar")
    public ResponseEntity<Void> ativar(@PathVariable Long id) {
        validacaoService.ativar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        validacaoService.inativar(id);
        return ResponseEntity.noContent().build();
    }
}
