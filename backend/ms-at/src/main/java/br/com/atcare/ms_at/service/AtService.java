package br.com.atcare.ms_at.service;

import br.com.atcare.ms_at.mapper.AtMapper;
import br.com.atcare.ms_at.model.dto.request.AtRequestDTO;
import br.com.atcare.ms_at.model.dto.response.AtResponseDTO;
import br.com.atcare.ms_at.model.entity.At;
import br.com.atcare.ms_at.repository.AtRepository;
import br.com.atcare.ms_at.repository.EspecialidadeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AtService {
    private final AtRepository repository;
    private final EspecialidadeRepository especialidadeRepository;
    private final AtMapper mapper;

    @Transactional
    public AtResponseDTO criar(AtRequestDTO dto) {
        At entity = mapper.toEntity(dto);
        resolverEspecialidades(entity);
        return mapper.toResponse(repository.save(entity));
    }

    @Transactional(readOnly = true)
    public List<AtResponseDTO> listarTodos() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public AtResponseDTO buscarPorId(Long id) {
        return mapper.toResponse(buscarEntidadePorId(id));
    }

    @Transactional
    public AtResponseDTO atualizar(Long id, AtRequestDTO dto) {
        At existente = buscarEntidadePorId(id);
        At dados = mapper.toEntity(dto);
        resolverEspecialidades(dados);
        existente.setPessoaId(dados.getPessoaId());
        existente.setFormacoes(dados.getFormacoes());
        existente.setLocaisAtendimento(dados.getLocaisAtendimento());
        existente.setEspecialidades(dados.getEspecialidades());
        existente.setDocumentos(dados.getDocumentos());
        existente.getFormacoes().forEach(item -> item.setAt(existente));
        existente.getLocaisAtendimento().forEach(item -> item.setAt(existente));
        existente.getEspecialidades().forEach(item -> item.setAt(existente));
        existente.getDocumentos().forEach(item -> item.setAt(existente));
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

    public At buscarEntidadePorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("AT não encontrado. Id: " + id));
    }

    private void resolverEspecialidades(At at) {
        at.getEspecialidades().forEach(item -> item.setEspecialidade(especialidadeRepository.findById(item.getEspecialidade().getId()).orElseThrow(() -> new EntityNotFoundException("Especialidade não encontrada. Id: " + item.getEspecialidade().getId()))));
    }

    private void alterarAtivo(Long id, boolean ativo) {
        At entity = buscarEntidadePorId(id);
        entity.setAtivo(ativo);
        repository.save(entity);
    }
}
