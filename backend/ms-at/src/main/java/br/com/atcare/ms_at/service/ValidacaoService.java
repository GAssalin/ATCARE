package br.com.atcare.ms_at.service;

import br.com.atcare.ms_at.mapper.ValidacaoMapper;
import br.com.atcare.ms_at.model.dto.request.ValidacaoRequestDTO;
import br.com.atcare.ms_at.model.dto.response.ValidacaoResponseDTO;
import br.com.atcare.ms_at.model.entity.Certificado;
import br.com.atcare.ms_at.model.entity.Validacao;
import br.com.atcare.ms_at.repository.CertificadoRepository;
import br.com.atcare.ms_at.repository.ValidacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ValidacaoService {
    private final ValidacaoRepository repository;
    private final CertificadoRepository certificadoRepository;
    private final ValidacaoMapper mapper;

    public ValidacaoResponseDTO criar(Long certificadoId, ValidacaoRequestDTO dto) {
        return mapper.toResponse(repository.save(mapper.toEntity(dto, buscarCertificado(certificadoId))));
    }

    public List<ValidacaoResponseDTO> listarTodos() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    public ValidacaoResponseDTO buscarPorId(Long id) {
        return mapper.toResponse(buscarEntidadePorId(id));
    }

    public ValidacaoResponseDTO atualizar(Long id, ValidacaoRequestDTO dto) {
        Validacao existente = buscarEntidadePorId(id);
        Validacao dados = mapper.toEntity(dto, existente.getCertificado());
        existente.setStatus(dados.getStatus());
        existente.setObservacao(dados.getObservacao());
        existente.setDataValidacao(dados.getDataValidacao());
        existente.setUsuarioValidador(dados.getUsuarioValidador());
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

    public Validacao buscarEntidadePorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Validação não encontrada. Id: " + id));
    }

    private Certificado buscarCertificado(Long id) {
        return certificadoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Certificado não encontrado. Id: " + id));
    }

    private void alterarAtivo(Long id, boolean ativo) {
        Validacao entity = buscarEntidadePorId(id);
        entity.setAtivo(ativo);
        repository.save(entity);
    }
}
