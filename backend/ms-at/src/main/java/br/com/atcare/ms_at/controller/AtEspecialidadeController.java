package br.com.atcare.ms_at.controller;

import br.com.atcare.ms_at.model.entity.AtEspecialidade;
import br.com.atcare.ms_at.service.AtEspecialidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/at-especialidades")
@RequiredArgsConstructor
public class AtEspecialidadeController {

    private final AtEspecialidadeService atEspecialidadeService;

    @PostMapping
    public ResponseEntity<AtEspecialidade> criar(@RequestBody AtEspecialidade entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(atEspecialidadeService.criar(entity));
    }

    @GetMapping
    public ResponseEntity<List<AtEspecialidade>> listarTodos() {
        return ResponseEntity.ok(atEspecialidadeService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtEspecialidade> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(atEspecialidadeService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtEspecialidade> atualizar(@PathVariable Long id, @RequestBody AtEspecialidade entity) {
        return ResponseEntity.ok(atEspecialidadeService.atualizar(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        atEspecialidadeService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/ativar")
    public ResponseEntity<Void> ativar(@PathVariable Long id) {
        atEspecialidadeService.ativar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        atEspecialidadeService.inativar(id);
        return ResponseEntity.noContent().build();
    }
}
