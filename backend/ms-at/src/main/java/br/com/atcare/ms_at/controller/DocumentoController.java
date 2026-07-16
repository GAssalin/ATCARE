package br.com.atcare.ms_at.controller;

import br.com.atcare.ms_at.model.dto.request.DocumentoRequestDTO;
import br.com.atcare.ms_at.model.dto.response.DocumentoResponseDTO;
import br.com.atcare.ms_at.service.DocumentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DocumentoController {
    private final DocumentoService service;

    @PostMapping("/ats/{atId}/documentos")
    public ResponseEntity<DocumentoResponseDTO> criar(@PathVariable Long atId, @Valid @RequestBody DocumentoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(atId, dto));
    }

    @GetMapping("/documentos")
    public ResponseEntity<List<DocumentoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/documentos/{id}")
    public ResponseEntity<DocumentoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/documentos/{id}")
    public ResponseEntity<DocumentoResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody DocumentoRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/documentos/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/documentos/{id}/ativar")
    public ResponseEntity<Void> ativar(@PathVariable Long id) {
        service.ativar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/documentos/{id}/inativar")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        service.inativar(id);
        return ResponseEntity.noContent().build();
    }
}
