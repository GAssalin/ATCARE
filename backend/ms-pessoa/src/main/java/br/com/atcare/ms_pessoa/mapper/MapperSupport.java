package br.com.atcare.ms_pessoa.mapper;

import br.com.atcare.core.base.model.EntidadeAuditavel;
import br.com.atcare.ms_pessoa.model.dto.response.AuditoriaResponse;
import br.com.atcare.ms_pessoa.model.dto.response.PessoaResumoResponse;
import br.com.atcare.ms_pessoa.model.entity.Pessoa;

final class MapperSupport {
    private MapperSupport() {}

    static AuditoriaResponse auditoria(EntidadeAuditavel entity) {
        if (entity == null) return null;
        return new AuditoriaResponse(entity.getCriadoPor(), entity.getCriadoEm(), entity.getAtualizadoPor(),
                entity.getAtualizadoEm(), entity.getAtivo());
    }

    static PessoaResumoResponse pessoa(Pessoa entity) {
        if (entity == null) return null;
        return new PessoaResumoResponse(entity.getId(), entity.getNome(), entity.getTipoPessoa());
    }
}
