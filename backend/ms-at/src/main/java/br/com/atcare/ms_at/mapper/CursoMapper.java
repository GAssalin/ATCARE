package br.com.atcare.ms_at.mapper;

import br.com.atcare.ms_at.model.dto.request.CursoRequestDTO;
import br.com.atcare.ms_at.model.dto.response.CursoResponseDTO;
import br.com.atcare.ms_at.model.entity.Curso;
import br.com.atcare.ms_at.model.entity.Formacao;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CursoMapper {
    private final CertificadoMapper certificadoMapper;

    public CursoMapper(CertificadoMapper certificadoMapper) {
        this.certificadoMapper = certificadoMapper;
    }

    public Curso toEntity(CursoRequestDTO dto, Formacao formacao) {
        if (dto == null) return null;
        Curso entity = new Curso();
        entity.setId(dto.id());
        entity.setFormacao(formacao);
        entity.setNome(dto.nome());
        entity.setInstituicao(dto.instituicao());
        entity.setCargaHoraria(dto.cargaHoraria());
        entity.setCertificados(dto.certificados() == null ? List.of() : dto.certificados().stream()
                .map(item -> certificadoMapper.toEntity(item, entity)).toList());
        return entity;
    }

    public CursoResponseDTO toResponse(Curso entity) {
        if (entity == null) return null;
        return new CursoResponseDTO(entity.getId(), entity.getNome(), entity.getInstituicao(),
                entity.getCargaHoraria(), entity.getCertificados() == null ? List.of()
                : entity.getCertificados().stream().map(certificadoMapper::toResponse).toList());
    }
}
