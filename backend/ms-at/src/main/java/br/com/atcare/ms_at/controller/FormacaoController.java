package br.com.atcare.ms_at.controller;

import br.com.atcare.ms_at.model.dto.request.FormacaoRequestDTO;
import br.com.atcare.ms_at.model.dto.response.FormacaoResponseDTO;
import br.com.atcare.ms_at.service.FormacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/v1") @RequiredArgsConstructor
public class FormacaoController {
    private final FormacaoService service;
    @PostMapping("/ats/{atId}/formacoes") public ResponseEntity<FormacaoResponseDTO> criar(@PathVariable Long atId, @Valid @RequestBody FormacaoRequestDTO dto) { return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(atId, dto)); }
    @GetMapping("/formacoes") public ResponseEntity<List<FormacaoResponseDTO>> listarTodos() { return ResponseEntity.ok(service.listarTodos()); }
    @GetMapping("/formacoes/{id}") public ResponseEntity<FormacaoResponseDTO> buscarPorId(@PathVariable Long id) { return ResponseEntity.ok(service.buscarPorId(id)); }
    @PutMapping("/formacoes/{id}") public ResponseEntity<FormacaoResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody FormacaoRequestDTO dto) { return ResponseEntity.ok(service.atualizar(id, dto)); }
    @DeleteMapping("/formacoes/{id}") public ResponseEntity<Void> deletar(@PathVariable Long id) { service.deletar(id); return ResponseEntity.noContent().build(); }
    @PatchMapping("/formacoes/{id}/ativar") public ResponseEntity<Void> ativar(@PathVariable Long id) { service.ativar(id); return ResponseEntity.noContent().build(); }
    @PatchMapping("/formacoes/{id}/inativar") public ResponseEntity<Void> inativar(@PathVariable Long id) { service.inativar(id); return ResponseEntity.noContent().build(); }
}
