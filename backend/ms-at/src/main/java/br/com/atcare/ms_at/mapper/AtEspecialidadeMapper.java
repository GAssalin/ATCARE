package br.com.atcare.ms_at.mapper;

import br.com.atcare.ms_at.model.dto.request.AtEspecialidadeRequestDTO;
import br.com.atcare.ms_at.model.dto.response.AtEspecialidadeResponseDTO;
import br.com.atcare.ms_at.model.entity.At;
import br.com.atcare.ms_at.model.entity.AtEspecialidade;
import br.com.atcare.ms_at.model.entity.Especialidade;
import org.springframework.stereotype.Component;

@Component
public class AtEspecialidadeMapper {
    private final EspecialidadeMapper especialidadeMapper;

    public AtEspecialidadeMapper(EspecialidadeMapper especialidadeMapper) {
        this.especialidadeMapper = especialidadeMapper;
    }

    public AtEspecialidade toEntity(AtEspecialidadeRequestDTO dto, At at) {
        if (dto == null) return null;
        Especialidade especialidade = new Especialidade();
        especialidade.setId(dto.especialidadeId());
        AtEspecialidade entity = new AtEspecialidade();
        entity.setId(dto.id());
        entity.setAt(at);
        entity.setEspecialidade(especialidade);
        return entity;
    }

    public AtEspecialidadeResponseDTO toResponse(AtEspecialidade entity) {
        if (entity == null) return null;
        return new AtEspecialidadeResponseDTO(entity.getId(),
                especialidadeMapper.toResponse(entity.getEspecialidade()));
    }
}
