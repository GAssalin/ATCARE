package br.com.atcare.ms_at.mapper;

import br.com.atcare.ms_at.model.dto.request.CertificadoRequestDTO;
import br.com.atcare.ms_at.model.dto.response.CertificadoResponseDTO;
import br.com.atcare.ms_at.model.entity.Certificado;
import br.com.atcare.ms_at.model.entity.Curso;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CertificadoMapper {
    private final ValidacaoMapper validacaoMapper;

    public CertificadoMapper(ValidacaoMapper validacaoMapper) {
        this.validacaoMapper = validacaoMapper;
    }

    public Certificado toEntity(CertificadoRequestDTO dto, Curso curso) {
        if (dto == null) return null;
        Certificado entity = new Certificado();
        entity.setId(dto.id());
        entity.setCurso(curso);
        entity.setArquivo(dto.arquivo());
        entity.setValidacoes(dto.validacoes() == null ? List.of() : dto.validacoes().stream()
                .map(item -> validacaoMapper.toEntity(item, entity)).toList());
        return entity;
    }

    public CertificadoResponseDTO toResponse(Certificado entity) {
        if (entity == null) return null;
        return new CertificadoResponseDTO(entity.getId(), entity.getArquivo(),
                entity.getValidacoes() == null ? List.of() : entity.getValidacoes().stream()
                        .map(validacaoMapper::toResponse).toList());
    }
}
