package br.com.atcare.ms_at.mapper;

import br.com.atcare.ms_at.model.dto.request.ValidacaoRequestDTO;
import br.com.atcare.ms_at.model.dto.response.ValidacaoResponseDTO;
import br.com.atcare.ms_at.model.entity.Certificado;
import br.com.atcare.ms_at.model.entity.Validacao;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoMapper {
    public Validacao toEntity(ValidacaoRequestDTO dto, Certificado certificado) {
        if (dto == null) return null;
        Validacao entity = new Validacao();
        entity.setId(dto.id());
        entity.setCertificado(certificado);
        entity.setStatus(dto.status());
        entity.setObservacao(dto.observacao());
        entity.setDataValidacao(dto.dataValidacao());
        entity.setUsuarioValidador(dto.usuarioValidador());
        return entity;
    }

    public ValidacaoResponseDTO toResponse(Validacao entity) {
        if (entity == null) return null;
        return new ValidacaoResponseDTO(entity.getId(), entity.getStatus(), entity.getObservacao(),
                entity.getDataValidacao(), entity.getUsuarioValidador());
    }
}
