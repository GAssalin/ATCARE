package br.com.atcare.ms_pessoa.mapper;

import br.com.atcare.ms_pessoa.model.dto.request.ContatoRequest;
import br.com.atcare.ms_pessoa.model.dto.response.ContatoResponse;
import br.com.atcare.ms_pessoa.model.entity.Contato;
import br.com.atcare.ms_pessoa.model.entity.Pessoa;
import org.springframework.stereotype.Component;

@Component
public class ContatoMapper {
    public Contato toEntity(ContatoRequest request, Pessoa pessoa) {
        Contato entity = new Contato();
        updateEntity(entity, request, pessoa);
        return entity;
    }

    public void updateEntity(Contato entity, ContatoRequest request, Pessoa pessoa) {
        entity.setPessoa(pessoa);
        entity.setTipo(request.tipo());
        entity.setValor(request.valor());
        entity.setPrincipal(request.principal());
    }

    public ContatoResponse toResponse(Contato entity) {
        if (entity == null) return null;
        return new ContatoResponse(entity.getId(), MapperSupport.pessoa(entity.getPessoa()), entity.getTipo(),
                entity.getValor(), entity.isPrincipal(), MapperSupport.auditoria(entity));
    }
}
