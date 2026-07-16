package br.com.atcare.ms_at.service;

import br.com.atcare.ms_at.model.dto.request.DocumentoRequestDTO;
import br.com.atcare.ms_at.model.dto.response.DocumentoResponseDTO;
import br.com.atcare.ms_at.model.entity.At;
import br.com.atcare.ms_at.model.entity.Documento;
import br.com.atcare.ms_at.model.mapper.DocumentoMapper;
import br.com.atcare.ms_at.repository.AtRepository;
import br.com.atcare.ms_at.repository.DocumentoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class DocumentoService {
    private final DocumentoRepository repository; private final AtRepository atRepository; private final DocumentoMapper mapper;
    public DocumentoResponseDTO criar(Long atId, DocumentoRequestDTO dto) { return mapper.toResponse(repository.save(mapper.toEntity(dto, buscarAt(atId)))); }
    public List<DocumentoResponseDTO> listarTodos() { return repository.findAll().stream().map(mapper::toResponse).toList(); }
    public DocumentoResponseDTO buscarPorId(Long id) { return mapper.toResponse(buscarEntidadePorId(id)); }
    public DocumentoResponseDTO atualizar(Long id, DocumentoRequestDTO dto) {
        Documento existente = buscarEntidadePorId(id); Documento dados = mapper.toEntity(dto, existente.getAt());
        existente.setNome(dados.getNome()); existente.setTipo(dados.getTipo());
        existente.setArquivoComprovante(dados.getArquivoComprovante()); existente.setObservacao(dados.getObservacao());
        return mapper.toResponse(repository.save(existente));
    }
    public void deletar(Long id) { repository.delete(buscarEntidadePorId(id)); }
    public void ativar(Long id) { alterarAtivo(id, true); } public void inativar(Long id) { alterarAtivo(id, false); }
    public Documento buscarEntidadePorId(Long id) { return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Documento não encontrado. Id: " + id)); }
    private At buscarAt(Long id) { return atRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("AT não encontrado. Id: " + id)); }
    private void alterarAtivo(Long id, boolean ativo) { Documento entity = buscarEntidadePorId(id); entity.setAtivo(ativo); repository.save(entity); }
}
