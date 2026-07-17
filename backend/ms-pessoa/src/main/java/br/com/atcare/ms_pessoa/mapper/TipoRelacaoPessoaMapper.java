package br.com.atcare.ms_pessoa.mapper;

import br.com.atcare.ms_pessoa.model.dto.request.TipoRelacaoPessoaRequest;
import br.com.atcare.ms_pessoa.model.dto.response.TipoRelacaoPessoaResponse;
import br.com.atcare.ms_pessoa.model.entity.TipoRelacaoPessoa;
import org.springframework.stereotype.Component;

@Component
public class TipoRelacaoPessoaMapper {
    public TipoRelacaoPessoa toEntity(TipoRelacaoPessoaRequest request) {
        TipoRelacaoPessoa entity = new TipoRelacaoPessoa();
        updateEntity(entity, request);
        return entity;
    }

    public void updateEntity(TipoRelacaoPessoa entity, TipoRelacaoPessoaRequest request) {
        entity.setNome(request.nome());
        entity.setDescricao(request.descricao());
    }

    public TipoRelacaoPessoaResponse toResponse(TipoRelacaoPessoa entity) {
        if (entity == null) return null;
        return new TipoRelacaoPessoaResponse(entity.getId(), entity.getNome(), entity.getDescricao(),
                MapperSupport.auditoria(entity));
    }
}
