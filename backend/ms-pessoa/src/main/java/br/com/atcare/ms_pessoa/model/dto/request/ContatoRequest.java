package br.com.atcare.ms_pessoa.model.dto.request;

import br.com.atcare.ms_pessoa.model.enums.TipoContato;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ContatoRequest(
        @NotNull Long pessoaId,
        @NotNull TipoContato tipo,
        @NotBlank @Size(max = 150) String valor,
        boolean principal
) {}
