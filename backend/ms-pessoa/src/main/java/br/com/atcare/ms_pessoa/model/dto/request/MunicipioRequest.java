package br.com.atcare.ms_pessoa.model.dto.request;

import br.com.atcare.ms_pessoa.model.enums.Uf;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record MunicipioRequest(
        @NotBlank @Size(max = 120) String nome,
        @NotNull Uf uf,
        @Pattern(regexp = "\\d{7}", message = "O código IBGE deve conter 7 dígitos") String codigoIbge
) {}
