package br.com.atcare.ms_at.service;

import br.com.atcare.ms_at.mapper.FormacaoMapper;
import br.com.atcare.ms_at.model.dto.request.FormacaoRequestDTO;
import br.com.atcare.ms_at.model.dto.response.FormacaoResponseDTO;
import br.com.atcare.ms_at.model.entity.At;
import br.com.atcare.ms_at.model.entity.Formacao;
import br.com.atcare.ms_at.repository.AtRepository;
import br.com.atcare.ms_at.repository.FormacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FormacaoService {
    private final FormacaoRepository repository;
    private final AtRepository atRepository;
    private final FormacaoMapper mapper;

    public FormacaoResponseDTO criar(Long atId, FormacaoRequestDTO dto) {
        return mapper.toResponse(repository.save(mapper.toEntity(dto, buscarAt(atId))));
    }

    public List<FormacaoResponseDTO> listarTodos() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    public FormacaoResponseDTO buscarPorId(Long id) {
        return mapper.toResponse(buscarEntidadePorId(id));
    }

    public FormacaoResponseDTO atualizar(Long id, FormacaoRequestDTO dto) {
        Formacao existente = buscarEntidadePorId(id);
        Formacao dados = mapper.toEntity(dto, existente.getAt());
        existente.setInstituicao(dados.getInstituicao());
        existente.setTitulo(dados.getTitulo());
        existente.setAnoConclusao(dados.getAnoConclusao());
        existente.setCursos(dados.getCursos());
        existente.getCursos().forEach(item -> item.setFormacao(existente));
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

    public Formacao buscarEntidadePorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Formação não encontrada. Id: " + id));
    }

    private At buscarAt(Long id) {
        return atRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("AT não encontrado. Id: " + id));
    }

    private void alterarAtivo(Long id, boolean ativo) {
        Formacao entity = buscarEntidadePorId(id);
        entity.setAtivo(ativo);
        repository.save(entity);
    }
}
