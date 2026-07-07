package br.com.atcare.ms_at.controller;

import br.com.atcare.ms_at.model.entity.At;
import br.com.atcare.ms_at.service.AtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ats")
@RequiredArgsConstructor
public class AtController {

    private final AtService atService;

    @PostMapping
    public ResponseEntity<At> criar(@RequestBody At entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(atService.criar(entity));
    }

    @GetMapping
    public ResponseEntity<List<At>> listarTodos() {
        return ResponseEntity.ok(atService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<At> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(atService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<At> atualizar(@PathVariable Long id, @RequestBody At entity) {
        return ResponseEntity.ok(atService.atualizar(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        atService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/ativar")
    public ResponseEntity<Void> ativar(@PathVariable Long id) {
        atService.ativar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        atService.inativar(id);
        return ResponseEntity.noContent().build();
    }
}
