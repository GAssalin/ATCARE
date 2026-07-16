package br.com.atcare.ms_at.service;

import br.com.atcare.ms_at.mapper.CursoMapper;
import br.com.atcare.ms_at.model.dto.request.CursoRequestDTO;
import br.com.atcare.ms_at.model.dto.response.CursoResponseDTO;
import br.com.atcare.ms_at.model.entity.Curso;
import br.com.atcare.ms_at.model.entity.Formacao;
import br.com.atcare.ms_at.repository.CursoRepository;
import br.com.atcare.ms_at.repository.FormacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoService {
    private final CursoRepository repository;
    private final FormacaoRepository formacaoRepository;
    private final CursoMapper mapper;

    public CursoResponseDTO criar(Long formacaoId, CursoRequestDTO dto) {
        return mapper.toResponse(repository.save(mapper.toEntity(dto, buscarFormacao(formacaoId))));
    }

    public List<CursoResponseDTO> listarTodos() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    public CursoResponseDTO buscarPorId(Long id) {
        return mapper.toResponse(buscarEntidadePorId(id));
    }

    public CursoResponseDTO atualizar(Long id, CursoRequestDTO dto) {
        Curso existente = buscarEntidadePorId(id);
        Curso dados = mapper.toEntity(dto, existente.getFormacao());
        existente.setNome(dados.getNome());
        existente.setInstituicao(dados.getInstituicao());
        existente.setCargaHoraria(dados.getCargaHoraria());
        existente.setCertificados(dados.getCertificados());
        existente.getCertificados().forEach(item -> item.setCurso(existente));
        return mapper.toResponse(repository.save(existente));
    }

    public void deletar(Long id) {
        repository.delete(buscarEntidadePorId(id));
    }

    public void ativar(Long id) {
        alterarAtivo(id, true);
    }

    public void inativar(Long id) {
        alterarAtivo(id, false);
    }

    public Curso buscarEntidadePorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Curso não encontrado. Id: " + id));
    }

    private Formacao buscarFormacao(Long id) {
        return formacaoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Formação não encontrada. Id: " + id));
    }

    private void alterarAtivo(Long id, boolean ativo) {
        Curso entity = buscarEntidadePorId(id);
        entity.setAtivo(ativo);
        repository.save(entity);
    }
}
