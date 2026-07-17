package br.com.atcare.ms_pessoa.mapper;

import br.com.atcare.ms_pessoa.model.dto.request.MunicipioRequest;
import br.com.atcare.ms_pessoa.model.dto.response.MunicipioResponse;
import br.com.atcare.ms_pessoa.model.entity.Municipio;
import org.springframework.stereotype.Component;

@Component
public class MunicipioMapper {
    public Municipio toEntity(MunicipioRequest request) {
        Municipio entity = new Municipio();
        updateEntity(entity, request);
        return entity;
    }

    public void updateEntity(Municipio entity, MunicipioRequest request) {
        entity.setNome(request.nome());
        entity.setUf(request.uf());
        entity.setCodigoIbge(request.codigoIbge());
    }

    public MunicipioResponse toResponse(Municipio entity) {
        if (entity == null) return null;
        return new MunicipioResponse(entity.getId(), entity.getNome(), entity.getUf(), entity.getCodigoIbge(),
                MapperSupport.auditoria(entity));
    }
}
