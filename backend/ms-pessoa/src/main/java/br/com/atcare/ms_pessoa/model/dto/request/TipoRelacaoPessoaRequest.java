package br.com.atcare.ms_pessoa.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TipoRelacaoPessoaRequest(
        @NotBlank @Size(max = 100) String nome,
        @Size(max = 255) String descricao
) {}
