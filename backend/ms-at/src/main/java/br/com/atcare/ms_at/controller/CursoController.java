package br.com.atcare.ms_at.controller;

import br.com.atcare.ms_at.model.dto.request.CursoRequestDTO;
import br.com.atcare.ms_at.model.dto.response.CursoResponseDTO;
import br.com.atcare.ms_at.service.CursoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/v1") @RequiredArgsConstructor
public class CursoController {
    private final CursoService service;
    @PostMapping("/formacoes/{formacaoId}/cursos") public ResponseEntity<CursoResponseDTO> criar(@PathVariable Long formacaoId, @Valid @RequestBody CursoRequestDTO dto) { return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(formacaoId, dto)); }
    @GetMapping("/cursos") public ResponseEntity<List<CursoResponseDTO>> listarTodos() { return ResponseEntity.ok(service.listarTodos()); }
    @GetMapping("/cursos/{id}") public ResponseEntity<CursoResponseDTO> buscarPorId(@PathVariable Long id) { return ResponseEntity.ok(service.buscarPorId(id)); }
    @PutMapping("/cursos/{id}") public ResponseEntity<CursoResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody CursoRequestDTO dto) { return ResponseEntity.ok(service.atualizar(id, dto)); }
    @DeleteMapping("/cursos/{id}") public ResponseEntity<Void> deletar(@PathVariable Long id) { service.deletar(id); return ResponseEntity.noContent().build(); }
    @PatchMapping("/cursos/{id}/ativar") public ResponseEntity<Void> ativar(@PathVariable Long id) { service.ativar(id); return ResponseEntity.noContent().build(); }
    @PatchMapping("/cursos/{id}/inativar") public ResponseEntity<Void> inativar(@PathVariable Long id) { service.inativar(id); return ResponseEntity.noContent().build(); }
}
