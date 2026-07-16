package br.com.atcare.ms_at.controller;

import br.com.atcare.ms_at.model.dto.request.LocalAtendimentoRequestDTO;
import br.com.atcare.ms_at.model.dto.response.LocalAtendimentoResponseDTO;
import br.com.atcare.ms_at.service.LocalAtendimentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/v1") @RequiredArgsConstructor
public class LocalAtendimentoController {
    private final LocalAtendimentoService service;
    @PostMapping("/ats/{atId}/locais-atendimento") public ResponseEntity<LocalAtendimentoResponseDTO> criar(@PathVariable Long atId, @Valid @RequestBody LocalAtendimentoRequestDTO dto) { return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(atId, dto)); }
    @GetMapping("/locais-atendimento") public ResponseEntity<List<LocalAtendimentoResponseDTO>> listarTodos() { return ResponseEntity.ok(service.listarTodos()); }
    @GetMapping("/locais-atendimento/{id}") public ResponseEntity<LocalAtendimentoResponseDTO> buscarPorId(@PathVariable Long id) { return ResponseEntity.ok(service.buscarPorId(id)); }
    @PutMapping("/locais-atendimento/{id}") public ResponseEntity<LocalAtendimentoResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody LocalAtendimentoRequestDTO dto) { return ResponseEntity.ok(service.atualizar(id, dto)); }
    @DeleteMapping("/locais-atendimento/{id}") public ResponseEntity<Void> deletar(@PathVariable Long id) { service.deletar(id); return ResponseEntity.noContent().build(); }
    @PatchMapping("/locais-atendimento/{id}/ativar") public ResponseEntity<Void> ativar(@PathVariable Long id) { service.ativar(id); return ResponseEntity.noContent().build(); }
    @PatchMapping("/locais-atendimento/{id}/inativar") public ResponseEntity<Void> inativar(@PathVariable Long id) { service.inativar(id); return ResponseEntity.noContent().build(); }
}
