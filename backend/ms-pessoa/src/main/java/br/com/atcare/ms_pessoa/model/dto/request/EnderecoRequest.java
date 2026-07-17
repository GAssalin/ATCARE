package br.com.atcare.ms_pessoa.model.dto.request;

import br.com.atcare.ms_pessoa.model.enums.TipoEndereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EnderecoRequest(
        @NotNull Long pessoaId,
        @NotNull TipoEndereco tipo,
        @NotBlank @Size(max = 150) String logradouro,
        @NotBlank @Size(max = 20) String numero,
        @Size(max = 100) String complemento,
        @NotBlank @Size(max = 100) String bairro,
        @NotNull Long municipioId,
        @NotBlank @Pattern(regexp = "\\d{8}", message = "O CEP deve conter 8 dígitos") String cep,
        boolean principal
) {}
