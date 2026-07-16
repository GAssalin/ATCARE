package br.com.atcare.ms_at.mapper;

import br.com.atcare.ms_at.model.dto.request.EspecialidadeRequestDTO;
import br.com.atcare.ms_at.model.dto.response.EspecialidadeResponseDTO;
import br.com.atcare.ms_at.model.entity.Especialidade;
import org.springframework.stereotype.Component;

@Component
public class EspecialidadeMapper {
    public Especialidade toEntity(EspecialidadeRequestDTO dto) {
        if (dto == null) return null;
        Especialidade entity = new Especialidade();
        entity.setId(dto.id());
        entity.setNome(dto.nome());
        entity.setDescricao(dto.descricao());
        return entity;
    }

    public EspecialidadeResponseDTO toResponse(Especialidade entity) {
        if (entity == null) return null;
        return new EspecialidadeResponseDTO(entity.getId(), entity.getNome(), entity.getDescricao());
    }
}
