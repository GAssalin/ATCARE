package br.com.atcare.ms_at.controller;

import br.com.atcare.ms_at.model.dto.request.CertificadoRequestDTO;
import br.com.atcare.ms_at.model.dto.response.CertificadoResponseDTO;
import br.com.atcare.ms_at.service.CertificadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CertificadoController {
    private final CertificadoService service;

    @PostMapping("/cursos/{cursoId}/certificados")
    public ResponseEntity<CertificadoResponseDTO> criar(@PathVariable Long cursoId, @Valid @RequestBody CertificadoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(cursoId, dto));
    }

    @GetMapping("/certificados")
    public ResponseEntity<List<CertificadoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/certificados/{id}")
    public ResponseEntity<CertificadoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/certificados/{id}")
    public ResponseEntity<CertificadoResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody CertificadoRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/certificados/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/certificados/{id}/ativar")
    public ResponseEntity<Void> ativar(@PathVariable Long id) {
        service.ativar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/certificados/{id}/inativar")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        service.inativar(id);
        return ResponseEntity.noContent().build();
    }
}
