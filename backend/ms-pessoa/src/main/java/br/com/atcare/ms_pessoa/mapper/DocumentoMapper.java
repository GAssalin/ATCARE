package br.com.atcare.ms_pessoa.mapper;

import br.com.atcare.ms_pessoa.model.dto.request.DocumentoRequest;
import br.com.atcare.ms_pessoa.model.dto.response.DocumentoResponse;
import br.com.atcare.ms_pessoa.model.entity.Documento;
import br.com.atcare.ms_pessoa.model.entity.Pessoa;
import org.springframework.stereotype.Component;

@Component
public class DocumentoMapper {
    public Documento toEntity(DocumentoRequest request, Pessoa pessoa) {
        Documento entity = new Documento();
        updateEntity(entity, request, pessoa);
        return entity;
    }

    public void updateEntity(Documento entity, DocumentoRequest request, Pessoa pessoa) {
        entity.setPessoa(pessoa);
        entity.setTipo(request.tipo());
        entity.setNumero(request.numero());
        entity.setOrgaoEmissor(request.orgaoEmissor());
        entity.setDataEmissao(request.dataEmissao());
    }

    public DocumentoResponse toResponse(Documento entity) {
        if (entity == null) return null;
        return new DocumentoResponse(entity.getId(), MapperSupport.pessoa(entity.getPessoa()), entity.getTipo(),
                entity.getNumero(), entity.getOrgaoEmissor(), entity.getDataEmissao(), MapperSupport.auditoria(entity));
    }
}
