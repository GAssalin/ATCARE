package br.com.atcare.ms_at.controller;

import br.com.atcare.ms_at.model.entity.AtDocumento;
import br.com.atcare.ms_at.service.AtDocumentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/at-documentos")
@RequiredArgsConstructor
public class AtDocumentoController {

    private final AtDocumentoService atDocumentoService;

    @PostMapping
    public ResponseEntity<AtDocumento> criar(@RequestBody AtDocumento entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(atDocumentoService.criar(entity));
    }

    @GetMapping
    public ResponseEntity<List<AtDocumento>> listarTodos() {
        return ResponseEntity.ok(atDocumentoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtDocumento> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(atDocumentoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtDocumento> atualizar(@PathVariable Long id, @RequestBody AtDocumento entity) {
        return ResponseEntity.ok(atDocumentoService.atualizar(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        atDocumentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/ativar")
    public ResponseEntity<Void> ativar(@PathVariable Long id) {
        atDocumentoService.ativar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        atDocumentoService.inativar(id);
        return ResponseEntity.noContent().build();
    }
}
