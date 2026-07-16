package br.com.atcare.ms_at.mapper;

import br.com.atcare.ms_at.model.dto.request.LocalAtendimentoRequestDTO;
import br.com.atcare.ms_at.model.dto.response.LocalAtendimentoResponseDTO;
import br.com.atcare.ms_at.model.entity.At;
import br.com.atcare.ms_at.model.entity.LocalAtendimento;
import org.springframework.stereotype.Component;

@Component
public class LocalAtendimentoMapper {
    private final EnderecoMapper enderecoMapper;

    public LocalAtendimentoMapper(EnderecoMapper enderecoMapper) {
        this.enderecoMapper = enderecoMapper;
    }

    public LocalAtendimento toEntity(LocalAtendimentoRequestDTO dto, At at) {
        if (dto == null) return null;
        LocalAtendimento entity = new LocalAtendimento();
        entity.setId(dto.id());
        entity.setAt(at);
        entity.setEndereco(enderecoMapper.toEntity(dto.endereco()));
        entity.setAtendimentoDomiciliar(dto.atendimentoDomiciliar());
        entity.setAtendimentoOnline(dto.atendimentoOnline());
        return entity;
    }

    public LocalAtendimentoResponseDTO toResponse(LocalAtendimento entity) {
        if (entity == null) return null;
        return new LocalAtendimentoResponseDTO(entity.getId(), enderecoMapper.toResponse(entity.getEndereco()),
                entity.getAtendimentoDomiciliar(), entity.getAtendimentoOnline());
    }
}
