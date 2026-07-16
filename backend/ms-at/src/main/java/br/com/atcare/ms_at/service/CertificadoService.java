package br.com.atcare.ms_at.service;

import br.com.atcare.ms_at.model.dto.request.CertificadoRequestDTO;
import br.com.atcare.ms_at.model.dto.response.CertificadoResponseDTO;
import br.com.atcare.ms_at.model.entity.Certificado;
import br.com.atcare.ms_at.model.entity.Curso;
import br.com.atcare.ms_at.model.mapper.CertificadoMapper;
import br.com.atcare.ms_at.repository.CertificadoRepository;
import br.com.atcare.ms_at.repository.CursoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class CertificadoService {
    private final CertificadoRepository repository; private final CursoRepository cursoRepository; private final CertificadoMapper mapper;
    public CertificadoResponseDTO criar(Long cursoId, CertificadoRequestDTO dto) { return mapper.toResponse(repository.save(mapper.toEntity(dto, buscarCurso(cursoId)))); }
    public List<CertificadoResponseDTO> listarTodos() { return repository.findAll().stream().map(mapper::toResponse).toList(); }
    public CertificadoResponseDTO buscarPorId(Long id) { return mapper.toResponse(buscarEntidadePorId(id)); }
    public CertificadoResponseDTO atualizar(Long id, CertificadoRequestDTO dto) {
        Certificado existente = buscarEntidadePorId(id); Certificado dados = mapper.toEntity(dto, existente.getCurso());
        existente.setArquivo(dados.getArquivo()); existente.setValidacoes(dados.getValidacoes());
        existente.getValidacoes().forEach(item -> item.setCertificado(existente));
        return mapper.toResponse(repository.save(existente));
    }
    public void deletar(Long id) { repository.delete(buscarEntidadePorId(id)); }
    public void ativar(Long id) { alterarAtivo(id, true); } public void inativar(Long id) { alterarAtivo(id, false); }
    public Certificado buscarEntidadePorId(Long id) { return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Certificado não encontrado. Id: " + id)); }
    private Curso buscarCurso(Long id) { return cursoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Curso não encontrado. Id: " + id)); }
    private void alterarAtivo(Long id, boolean ativo) { Certificado entity = buscarEntidadePorId(id); entity.setAtivo(ativo); repository.save(entity); }
}
