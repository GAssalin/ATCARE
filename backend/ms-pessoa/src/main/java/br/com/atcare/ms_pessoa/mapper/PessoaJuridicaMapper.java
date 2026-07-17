package br.com.atcare.ms_pessoa.mapper;

import br.com.atcare.ms_pessoa.model.dto.request.PessoaJuridicaRequest;
import br.com.atcare.ms_pessoa.model.dto.response.PessoaJuridicaResponse;
import br.com.atcare.ms_pessoa.model.entity.PessoaJuridica;
import br.com.atcare.ms_pessoa.model.enums.TipoPessoa;
import org.springframework.stereotype.Component;

@Component
public class PessoaJuridicaMapper {
    public PessoaJuridica toEntity(PessoaJuridicaRequest request) {
        PessoaJuridica entity = new PessoaJuridica();
        entity.setTipoPessoa(TipoPessoa.JURIDICA);
        updateEntity(entity, request);
        return entity;
    }

    public void updateEntity(PessoaJuridica entity, PessoaJuridicaRequest request) {
        entity.setNome(request.nome());
        entity.setCnpj(request.cnpj());
        entity.setRazaoSocial(request.razaoSocial());
        entity.setNomeFantasia(request.nomeFantasia());
    }

    public PessoaJuridicaResponse toResponse(PessoaJuridica entity) {
        if (entity == null) return null;
        return new PessoaJuridicaResponse(entity.getId(), entity.getNome(), entity.getTipoPessoa(), entity.getCnpj(),
                entity.getRazaoSocial(), entity.getNomeFantasia(), MapperSupport.auditoria(entity));
    }
}
