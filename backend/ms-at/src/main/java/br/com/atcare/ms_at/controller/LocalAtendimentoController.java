package br.com.atcare.ms_at.controller;

import br.com.atcare.ms_at.model.entity.LocalAtendimento;
import br.com.atcare.ms_at.service.LocalAtendimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locais-atendimento")
@RequiredArgsConstructor
public class LocalAtendimentoController {

    private final LocalAtendimentoService localAtendimentoService;

    @PostMapping
    public ResponseEntity<LocalAtendimento> criar(@RequestBody LocalAtendimento entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(localAtendimentoService.criar(entity));
    }

    @GetMapping
    public ResponseEntity<List<LocalAtendimento>> listarTodos() {
        return ResponseEntity.ok(localAtendimentoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalAtendimento> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(localAtendimentoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocalAtendimento> atualizar(@PathVariable Long id, @RequestBody LocalAtendimento entity) {
        return ResponseEntity.ok(localAtendimentoService.atualizar(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        localAtendimentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/ativar")
    public ResponseEntity<Void> ativar(@PathVariable Long id) {
        localAtendimentoService.ativar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        localAtendimentoService.inativar(id);
        return ResponseEntity.noContent().build();
    }
}
