package br.com.atcare.ms_at.service;

import br.com.atcare.ms_at.model.dto.request.LocalAtendimentoRequestDTO;
import br.com.atcare.ms_at.model.dto.response.LocalAtendimentoResponseDTO;
import br.com.atcare.ms_at.model.entity.At;
import br.com.atcare.ms_at.model.entity.Endereco;
import br.com.atcare.ms_at.model.entity.LocalAtendimento;
import br.com.atcare.ms_at.model.mapper.LocalAtendimentoMapper;
import br.com.atcare.ms_at.repository.AtRepository;
import br.com.atcare.ms_at.repository.EnderecoRepository;
import br.com.atcare.ms_at.repository.LocalAtendimentoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor
public class LocalAtendimentoService {
    private final LocalAtendimentoRepository repository; private final AtRepository atRepository;
    private final EnderecoRepository enderecoRepository; private final LocalAtendimentoMapper mapper;
    @Transactional
    public LocalAtendimentoResponseDTO criar(Long atId, LocalAtendimentoRequestDTO dto) {
        LocalAtendimento entity = mapper.toEntity(dto, buscarAt(atId));
        if (entity.getEndereco() != null) entity.setEndereco(enderecoRepository.save(entity.getEndereco()));
        return mapper.toResponse(repository.save(entity));
    }
    public List<LocalAtendimentoResponseDTO> listarTodos() { return repository.findAll().stream().map(mapper::toResponse).toList(); }
    public LocalAtendimentoResponseDTO buscarPorId(Long id) { return mapper.toResponse(buscarEntidadePorId(id)); }
    @Transactional
    public LocalAtendimentoResponseDTO atualizar(Long id, LocalAtendimentoRequestDTO dto) {
        LocalAtendimento existente = buscarEntidadePorId(id); LocalAtendimento dados = mapper.toEntity(dto, existente.getAt());
        Endereco endereco = dados.getEndereco() == null ? null : enderecoRepository.save(dados.getEndereco());
        existente.setEndereco(endereco); existente.setAtendimentoDomiciliar(dados.getAtendimentoDomiciliar());
        existente.setAtendimentoOnline(dados.getAtendimentoOnline());
        return mapper.toResponse(repository.save(existente));
    }
    public void deletar(Long id) { repository.delete(buscarEntidadePorId(id)); }
    public void ativar(Long id) { alterarAtivo(id, true); } public void inativar(Long id) { alterarAtivo(id, false); }
    public LocalAtendimento buscarEntidadePorId(Long id) { return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Local de atendimento não encontrado. Id: " + id)); }
    private At buscarAt(Long id) { return atRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("AT não encontrado. Id: " + id)); }
    private void alterarAtivo(Long id, boolean ativo) { LocalAtendimento entity = buscarEntidadePorId(id); entity.setAtivo(ativo); repository.save(entity); }
}
