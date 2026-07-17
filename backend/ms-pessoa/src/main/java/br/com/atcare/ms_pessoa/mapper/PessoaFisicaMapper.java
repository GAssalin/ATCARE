package br.com.atcare.ms_pessoa.mapper;

import br.com.atcare.ms_pessoa.model.dto.request.PessoaFisicaRequest;
import br.com.atcare.ms_pessoa.model.dto.response.PessoaFisicaResponse;
import br.com.atcare.ms_pessoa.model.entity.PessoaFisica;
import br.com.atcare.ms_pessoa.model.enums.TipoPessoa;
import org.springframework.stereotype.Component;

@Component
public class PessoaFisicaMapper {
    public PessoaFisica toEntity(PessoaFisicaRequest request) {
        PessoaFisica entity = new PessoaFisica();
        entity.setTipoPessoa(TipoPessoa.FISICA);
        updateEntity(entity, request);
        return entity;
    }

    public void updateEntity(PessoaFisica entity, PessoaFisicaRequest request) {
        entity.setNome(request.nome());
        entity.setCpf(request.cpf());
        entity.setDataNascimento(request.dataNascimento());
        entity.setNomeSocial(request.nomeSocial());
    }

    public PessoaFisicaResponse toResponse(PessoaFisica entity) {
        if (entity == null) return null;
        return new PessoaFisicaResponse(entity.getId(), entity.getNome(), entity.getTipoPessoa(), entity.getCpf(),
                entity.getDataNascimento(), entity.getNomeSocial(), MapperSupport.auditoria(entity));
    }
}
