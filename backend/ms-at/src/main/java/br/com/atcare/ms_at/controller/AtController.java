package br.com.atcare.ms_at.controller;

import br.com.atcare.ms_at.model.dto.request.AtRequestDTO;
import br.com.atcare.ms_at.model.dto.response.AtResponseDTO;
import br.com.atcare.ms_at.service.AtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/v1/ats") @RequiredArgsConstructor
public class AtController {
    private final AtService service;
    @PostMapping public ResponseEntity<AtResponseDTO> criar(@Valid @RequestBody AtRequestDTO dto) { return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto)); }
    @GetMapping public ResponseEntity<List<AtResponseDTO>> listarTodos() { return ResponseEntity.ok(service.listarTodos()); }
    @GetMapping("/{id}") public ResponseEntity<AtResponseDTO> buscarPorId(@PathVariable Long id) { return ResponseEntity.ok(service.buscarPorId(id)); }
    @PutMapping("/{id}") public ResponseEntity<AtResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody AtRequestDTO dto) { return ResponseEntity.ok(service.atualizar(id, dto)); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> deletar(@PathVariable Long id) { service.deletar(id); return ResponseEntity.noContent().build(); }
    @PatchMapping("/{id}/ativar") public ResponseEntity<Void> ativar(@PathVariable Long id) { service.ativar(id); return ResponseEntity.noContent().build(); }
    @PatchMapping("/{id}/inativar") public ResponseEntity<Void> inativar(@PathVariable Long id) { service.inativar(id); return ResponseEntity.noContent().build(); }
}
