package br.com.atcare.ms_at.controller;

import br.com.atcare.ms_at.model.entity.Certificado;
import br.com.atcare.ms_at.service.CertificadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/certificados")
@RequiredArgsConstructor
public class CertificadoController {

    private final CertificadoService certificadoService;

    @PostMapping
    public ResponseEntity<Certificado> criar(@RequestBody Certificado entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(certificadoService.criar(entity));
    }

    @GetMapping
    public ResponseEntity<List<Certificado>> listarTodos() {
        return ResponseEntity.ok(certificadoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Certificado> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(certificadoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Certificado> atualizar(@PathVariable Long id, @RequestBody Certificado entity) {
        return ResponseEntity.ok(certificadoService.atualizar(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        certificadoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/ativar")
    public ResponseEntity<Void> ativar(@PathVariable Long id) {
        certificadoService.ativar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        certificadoService.inativar(id);
        return ResponseEntity.noContent().build();
    }
}
