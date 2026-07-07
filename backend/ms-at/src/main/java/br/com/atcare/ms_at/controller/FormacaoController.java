package br.com.atcare.ms_at.controller;

import br.com.atcare.ms_at.model.entity.Formacao;
import br.com.atcare.ms_at.service.FormacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formacoes")
@RequiredArgsConstructor
public class FormacaoController {

    private final FormacaoService formacaoService;

    @PostMapping
    public ResponseEntity<Formacao> criar(@RequestBody Formacao entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(formacaoService.criar(entity));
    }

    @GetMapping
    public ResponseEntity<List<Formacao>> listarTodos() {
        return ResponseEntity.ok(formacaoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Formacao> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(formacaoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Formacao> atualizar(@PathVariable Long id, @RequestBody Formacao entity) {
        return ResponseEntity.ok(formacaoService.atualizar(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        formacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/ativar")
    public ResponseEntity<Void> ativar(@PathVariable Long id) {
        formacaoService.ativar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        formacaoService.inativar(id);
        return ResponseEntity.noContent().build();
    }
}
