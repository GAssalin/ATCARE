package br.com.atcare.ms_at.controller;

import br.com.atcare.ms_at.model.dto.request.ValidacaoRequestDTO;
import br.com.atcare.ms_at.model.dto.response.ValidacaoResponseDTO;
import br.com.atcare.ms_at.service.ValidacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/v1") @RequiredArgsConstructor
public class ValidacaoController {
    private final ValidacaoService service;
    @PostMapping("/certificados/{certificadoId}/validacoes") public ResponseEntity<ValidacaoResponseDTO> criar(@PathVariable Long certificadoId, @Valid @RequestBody ValidacaoRequestDTO dto) { return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(certificadoId, dto)); }
    @GetMapping("/validacoes") public ResponseEntity<List<ValidacaoResponseDTO>> listarTodos() { return ResponseEntity.ok(service.listarTodos()); }
    @GetMapping("/validacoes/{id}") public ResponseEntity<ValidacaoResponseDTO> buscarPorId(@PathVariable Long id) { return ResponseEntity.ok(service.buscarPorId(id)); }
    @PutMapping("/validacoes/{id}") public ResponseEntity<ValidacaoResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ValidacaoRequestDTO dto) { return ResponseEntity.ok(service.atualizar(id, dto)); }
    @DeleteMapping("/validacoes/{id}") public ResponseEntity<Void> deletar(@PathVariable Long id) { service.deletar(id); return ResponseEntity.noContent().build(); }
    @PatchMapping("/validacoes/{id}/ativar") public ResponseEntity<Void> ativar(@PathVariable Long id) { service.ativar(id); return ResponseEntity.noContent().build(); }
    @PatchMapping("/validacoes/{id}/inativar") public ResponseEntity<Void> inativar(@PathVariable Long id) { service.inativar(id); return ResponseEntity.noContent().build(); }
}
