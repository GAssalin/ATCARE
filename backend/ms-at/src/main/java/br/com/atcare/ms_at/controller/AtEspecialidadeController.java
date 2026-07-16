package br.com.atcare.ms_at.controller;

import br.com.atcare.ms_at.model.dto.request.AtEspecialidadeRequestDTO;
import br.com.atcare.ms_at.model.dto.response.AtEspecialidadeResponseDTO;
import br.com.atcare.ms_at.service.AtEspecialidadeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/v1") @RequiredArgsConstructor
public class AtEspecialidadeController {
    private final AtEspecialidadeService service;
    @PostMapping("/ats/{atId}/especialidades") public ResponseEntity<AtEspecialidadeResponseDTO> criar(@PathVariable Long atId, @Valid @RequestBody AtEspecialidadeRequestDTO dto) { return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(atId, dto)); }
    @GetMapping("/at-especialidades") public ResponseEntity<List<AtEspecialidadeResponseDTO>> listarTodos() { return ResponseEntity.ok(service.listarTodos()); }
    @GetMapping("/at-especialidades/{id}") public ResponseEntity<AtEspecialidadeResponseDTO> buscarPorId(@PathVariable Long id) { return ResponseEntity.ok(service.buscarPorId(id)); }
    @PutMapping("/at-especialidades/{id}") public ResponseEntity<AtEspecialidadeResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody AtEspecialidadeRequestDTO dto) { return ResponseEntity.ok(service.atualizar(id, dto)); }
    @DeleteMapping("/at-especialidades/{id}") public ResponseEntity<Void> deletar(@PathVariable Long id) { service.deletar(id); return ResponseEntity.noContent().build(); }
    @PatchMapping("/at-especialidades/{id}/ativar") public ResponseEntity<Void> ativar(@PathVariable Long id) { service.ativar(id); return ResponseEntity.noContent().build(); }
    @PatchMapping("/at-especialidades/{id}/inativar") public ResponseEntity<Void> inativar(@PathVariable Long id) { service.inativar(id); return ResponseEntity.noContent().build(); }
}
