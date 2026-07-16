package br.com.atcare.ms_at.mapper;

import br.com.atcare.ms_at.model.dto.request.AtRequestDTO;
import br.com.atcare.ms_at.model.dto.response.AtResponseDTO;
import br.com.atcare.ms_at.model.entity.At;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AtMapper {
    private final FormacaoMapper formacaoMapper;
    private final LocalAtendimentoMapper localAtendimentoMapper;
    private final AtEspecialidadeMapper atEspecialidadeMapper;
    private final DocumentoMapper documentoMapper;

    public AtMapper(FormacaoMapper formacaoMapper, LocalAtendimentoMapper localAtendimentoMapper,
                    AtEspecialidadeMapper atEspecialidadeMapper, DocumentoMapper documentoMapper) {
        this.formacaoMapper = formacaoMapper;
        this.localAtendimentoMapper = localAtendimentoMapper;
        this.atEspecialidadeMapper = atEspecialidadeMapper;
        this.documentoMapper = documentoMapper;
    }

    public At toEntity(AtRequestDTO dto) {
        if (dto == null) return null;
        At entity = new At();
        entity.setId(dto.id());
        entity.setPessoaId(dto.pessoaId());
        entity.setFormacoes(dto.formacoes() == null ? List.of() : dto.formacoes().stream()
                .map(item -> formacaoMapper.toEntity(item, entity)).toList());
        entity.setLocaisAtendimento(dto.locaisAtendimento() == null ? List.of()
                : dto.locaisAtendimento().stream()
                .map(item -> localAtendimentoMapper.toEntity(item, entity)).toList());
        entity.setEspecialidades(dto.especialidades() == null ? List.of()
                : dto.especialidades().stream()
                .map(item -> atEspecialidadeMapper.toEntity(item, entity)).toList());
        entity.setDocumentos(dto.documentos() == null ? List.of() : dto.documentos().stream()
                .map(item -> documentoMapper.toEntity(item, entity)).toList());
        return entity;
    }

    public AtResponseDTO toResponse(At entity) {
        if (entity == null) return null;
        return new AtResponseDTO(entity.getId(), entity.getPessoaId(),
                entity.getFormacoes() == null ? List.of()
                        : entity.getFormacoes().stream().map(formacaoMapper::toResponse).toList(),
                entity.getLocaisAtendimento() == null ? List.of()
                        : entity.getLocaisAtendimento().stream().map(localAtendimentoMapper::toResponse).toList(),
                entity.getEspecialidades() == null ? List.of()
                        : entity.getEspecialidades().stream().map(atEspecialidadeMapper::toResponse).toList(),
                entity.getDocumentos() == null ? List.of()
                        : entity.getDocumentos().stream().map(documentoMapper::toResponse).toList());
    }
}
