package br.com.atcare.ms_pessoa.mapper;

import br.com.atcare.ms_pessoa.model.dto.request.EnderecoRequest;
import br.com.atcare.ms_pessoa.model.dto.response.EnderecoResponse;
import br.com.atcare.ms_pessoa.model.entity.Endereco;
import br.com.atcare.ms_pessoa.model.entity.Municipio;
import br.com.atcare.ms_pessoa.model.entity.Pessoa;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapper {
    private final MunicipioMapper municipioMapper;

    public EnderecoMapper(MunicipioMapper municipioMapper) {
        this.municipioMapper = municipioMapper;
    }

    public Endereco toEntity(EnderecoRequest request, Pessoa pessoa, Municipio municipio) {
        Endereco entity = new Endereco();
        updateEntity(entity, request, pessoa, municipio);
        return entity;
    }

    public void updateEntity(Endereco entity, EnderecoRequest request, Pessoa pessoa, Municipio municipio) {
        entity.setPessoa(pessoa);
        entity.setTipo(request.tipo());
        entity.setLogradouro(request.logradouro());
        entity.setNumero(request.numero());
        entity.setComplemento(request.complemento());
        entity.setBairro(request.bairro());
        entity.setMunicipio(municipio);
        entity.setCep(request.cep());
        entity.setPrincipal(request.principal());
    }

    public EnderecoResponse toResponse(Endereco entity) {
        if (entity == null) return null;
        return new EnderecoResponse(entity.getId(), MapperSupport.pessoa(entity.getPessoa()), entity.getTipo(),
                entity.getLogradouro(), entity.getNumero(), entity.getComplemento(), entity.getBairro(),
                municipioMapper.toResponse(entity.getMunicipio()), entity.getCep(), entity.isPrincipal(),
                MapperSupport.auditoria(entity));
    }
}
