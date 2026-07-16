package br.com.atcare.ms_at.mapper;

import br.com.atcare.ms_at.model.dto.request.FormacaoRequestDTO;
import br.com.atcare.ms_at.model.dto.response.FormacaoResponseDTO;
import br.com.atcare.ms_at.model.entity.At;
import br.com.atcare.ms_at.model.entity.Formacao;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FormacaoMapper {
    private final CursoMapper cursoMapper;

    public FormacaoMapper(CursoMapper cursoMapper) {
        this.cursoMapper = cursoMapper;
    }

    public Formacao toEntity(FormacaoRequestDTO dto, At at) {
        if (dto == null) return null;
        Formacao entity = new Formacao();
        entity.setId(dto.id());
        entity.setAt(at);
        entity.setInstituicao(dto.instituicao());
        entity.setTitulo(dto.titulo());
        entity.setAnoConclusao(dto.anoConclusao());
        entity.setCursos(dto.cursos() == null ? List.of() : dto.cursos().stream()
                .map(item -> cursoMapper.toEntity(item, entity)).toList());
        return entity;
    }

    public FormacaoResponseDTO toResponse(Formacao entity) {
        if (entity == null) return null;
        return new FormacaoResponseDTO(entity.getId(), entity.getInstituicao(), entity.getTitulo(),
                entity.getAnoConclusao(), entity.getCursos() == null ? List.of()
                : entity.getCursos().stream().map(cursoMapper::toResponse).toList());
    }
}
