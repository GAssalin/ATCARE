package br.com.atcare.ms_at.controller;

import br.com.atcare.ms_at.model.dto.request.EnderecoRequestDTO;
import br.com.atcare.ms_at.model.dto.response.EnderecoResponseDTO;
import br.com.atcare.ms_at.service.EnderecoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/v1/enderecos") @RequiredArgsConstructor
public class EnderecoController {
    private final EnderecoService service;
    @PostMapping public ResponseEntity<EnderecoResponseDTO> criar(@Valid @RequestBody EnderecoRequestDTO dto) { return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto)); }
    @GetMapping public ResponseEntity<List<EnderecoResponseDTO>> listarTodos() { return ResponseEntity.ok(service.listarTodos()); }
    @GetMapping("/{id}") public ResponseEntity<EnderecoResponseDTO> buscarPorId(@PathVariable Long id) { return ResponseEntity.ok(service.buscarPorId(id)); }
    @PutMapping("/{id}") public ResponseEntity<EnderecoResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody EnderecoRequestDTO dto) { return ResponseEntity.ok(service.atualizar(id, dto)); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> deletar(@PathVariable Long id) { service.deletar(id); return ResponseEntity.noContent().build(); }
    @PatchMapping("/{id}/ativar") public ResponseEntity<Void> ativar(@PathVariable Long id) { service.ativar(id); return ResponseEntity.noContent().build(); }
    @PatchMapping("/{id}/inativar") public ResponseEntity<Void> inativar(@PathVariable Long id) { service.inativar(id); return ResponseEntity.noContent().build(); }
}
