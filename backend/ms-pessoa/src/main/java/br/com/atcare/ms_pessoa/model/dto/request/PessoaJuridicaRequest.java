package br.com.atcare.ms_pessoa.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PessoaJuridicaRequest(
        @NotBlank @Size(max = 150) String nome,
        @NotBlank @Pattern(regexp = "\\d{14}", message = "O CNPJ deve conter 14 dígitos") String cnpj,
        @NotBlank @Size(max = 200) String razaoSocial,
        @Size(max = 200) String nomeFantasia
) {}
