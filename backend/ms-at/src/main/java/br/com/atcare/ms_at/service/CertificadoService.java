package br.com.atcare.ms_at.service;

import br.com.atcare.ms_at.model.entity.Certificado;
import br.com.atcare.ms_at.repository.CertificadoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificadoService {

    private final CertificadoRepository repository;

    public Certificado criar(Certificado entity) {
        return repository.save(entity);
    }

    public List<Certificado> listarTodos() {
        return repository.findAll();
    }

    public Certificado buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Certificado não encontrado. Id: " + id));
    }

    public Certificado atualizar(Long id, Certificado entity) {
        Certificado existente = buscarPorId(id);

        existente.setCurso(entity.getCurso());
        existente.setArquivo(entity.getArquivo());
        existente.setValidacoes(entity.getValidacoes());

        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.delete(buscarPorId(id));
    }

    public void ativar(Long id) {
        Certificado entity = buscarPorId(id);
        entity.setAtivo(true);
        repository.save(entity);
    }

    public void inativar(Long id) {
        Certificado entity = buscarPorId(id);
        entity.setAtivo(false);
        repository.save(entity);
    }
}