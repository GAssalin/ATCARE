package br.com.atcare.ms_at.mapper;

import br.com.atcare.ms_at.model.dto.request.DocumentoRequestDTO;
import br.com.atcare.ms_at.model.dto.response.DocumentoResponseDTO;
import br.com.atcare.ms_at.model.entity.At;
import br.com.atcare.ms_at.model.entity.Documento;
import org.springframework.stereotype.Component;

@Component
public class DocumentoMapper {
    public Documento toEntity(DocumentoRequestDTO dto, At at) {
        if (dto == null) return null;
        Documento entity = new Documento();
        entity.setId(dto.id());
        entity.setAt(at);
        entity.setNome(dto.nome());
        entity.setTipo(dto.tipo());
        entity.setArquivoComprovante(dto.arquivoComprovante());
        entity.setObservacao(dto.observacao());
        return entity;
    }

    public DocumentoResponseDTO toResponse(Documento entity) {
        if (entity == null) return null;
        return new DocumentoResponseDTO(entity.getId(), entity.getNome(), entity.getTipo(),
                entity.getArquivoComprovante(), entity.getObservacao());
    }
}
