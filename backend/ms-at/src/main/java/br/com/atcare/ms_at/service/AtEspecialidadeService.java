package br.com.atcare.ms_at.service;

import br.com.atcare.ms_at.mapper.AtEspecialidadeMapper;
import br.com.atcare.ms_at.model.dto.request.AtEspecialidadeRequestDTO;
import br.com.atcare.ms_at.model.dto.response.AtEspecialidadeResponseDTO;
import br.com.atcare.ms_at.model.entity.At;
import br.com.atcare.ms_at.model.entity.AtEspecialidade;
import br.com.atcare.ms_at.model.entity.Especialidade;
import br.com.atcare.ms_at.repository.AtEspecialidadeRepository;
import br.com.atcare.ms_at.repository.AtRepository;
import br.com.atcare.ms_at.repository.EspecialidadeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AtEspecialidadeService {
    private final AtEspecialidadeRepository repository;
    private final AtRepository atRepository;
    private final EspecialidadeRepository especialidadeRepository;
    private final AtEspecialidadeMapper mapper;

    public AtEspecialidadeResponseDTO criar(Long atId, AtEspecialidadeRequestDTO dto) {
        AtEspecialidade entity = mapper.toEntity(dto, buscarAt(atId));
        entity.setEspecialidade(buscarEspecialidade(dto.especialidadeId()));
        return mapper.toResponse(repository.save(entity));
    }

    public List<AtEspecialidadeResponseDTO> listarTodos() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    public AtEspecialidadeResponseDTO buscarPorId(Long id) {
        return mapper.toResponse(buscarEntidadePorId(id));
    }

    public AtEspecialidadeResponseDTO atualizar(Long id, AtEspecialidadeRequestDTO dto) {
        AtEspecialidade existente = buscarEntidadePorId(id);
        existente.setEspecialidade(buscarEspecialidade(dto.especialidadeId()));
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

    public AtEspecialidade buscarEntidadePorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Especialidade do AT não encontrada. Id: " + id));
    }

    private At buscarAt(Long id) {
        return atRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("AT não encontrado. Id: " + id));
    }

    private Especialidade buscarEspecialidade(Long id) {
        return especialidadeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Especialidade não encontrada. Id: " + id));
    }

    private void alterarAtivo(Long id, boolean ativo) {
        AtEspecialidade entity = buscarEntidadePorId(id);
        entity.setAtivo(ativo);
        repository.save(entity);
    }
}
