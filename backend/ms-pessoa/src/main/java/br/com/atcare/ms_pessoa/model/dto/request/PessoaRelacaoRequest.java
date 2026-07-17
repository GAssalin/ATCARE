package br.com.atcare.ms_pessoa.model.dto.request;

import jakarta.validation.constraints.NotNull;

public record PessoaRelacaoRequest(
        @NotNull Long pessoaId,
        @NotNull Long relacionadoId,
        @NotNull Long tipoRelacaoId
) {}
