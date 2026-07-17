package br.com.atcare.ms_pessoa.mapper;

import br.com.atcare.ms_pessoa.model.dto.request.PessoaRelacaoRequest;
import br.com.atcare.ms_pessoa.model.dto.response.PessoaRelacaoResponse;
import br.com.atcare.ms_pessoa.model.entity.Pessoa;
import br.com.atcare.ms_pessoa.model.entity.PessoaRelacao;
import br.com.atcare.ms_pessoa.model.entity.TipoRelacaoPessoa;
import org.springframework.stereotype.Component;

@Component
public class PessoaRelacaoMapper {
    private final TipoRelacaoPessoaMapper tipoRelacaoMapper;

    public PessoaRelacaoMapper(TipoRelacaoPessoaMapper tipoRelacaoMapper) {
        this.tipoRelacaoMapper = tipoRelacaoMapper;
    }

    public PessoaRelacao toEntity(PessoaRelacaoRequest request, Pessoa pessoa, Pessoa relacionado,
                                  TipoRelacaoPessoa tipoRelacao) {
        PessoaRelacao entity = new PessoaRelacao();
        updateEntity(entity, request, pessoa, relacionado, tipoRelacao);
        return entity;
    }

    public void updateEntity(PessoaRelacao entity, PessoaRelacaoRequest request, Pessoa pessoa,
                             Pessoa relacionado, TipoRelacaoPessoa tipoRelacao) {
        entity.setPessoa(pessoa);
        entity.setRelacionado(relacionado);
        entity.setTipoRelacao(tipoRelacao);
    }

    public PessoaRelacaoResponse toResponse(PessoaRelacao entity) {
        if (entity == null) return null;
        return new PessoaRelacaoResponse(entity.getId(), MapperSupport.pessoa(entity.getPessoa()),
                MapperSupport.pessoa(entity.getRelacionado()), tipoRelacaoMapper.toResponse(entity.getTipoRelacao()),
                MapperSupport.auditoria(entity));
    }
}
