package br.com.atcare.ms_at.model.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EnderecoRequestDTO(
        Long id,
        @Pattern(regexp = "\\d{5}-?\\d{3}", message = "O CEP deve possuir 8 dígitos") String cep,
        @Size(max = 150, message = "O logradouro deve possuir no máximo 150 caracteres") String logradouro,
        @Size(max = 20, message = "O número deve possuir no máximo 20 caracteres") String numero,
        @Size(max = 100, message = "O complemento deve possuir no máximo 100 caracteres") String complemento,
        @Size(max = 100, message = "O bairro deve possuir no máximo 100 caracteres") String bairro,
        @Size(max = 100, message = "A cidade deve possuir no máximo 100 caracteres") String cidade,
        @Pattern(regexp = "[A-Za-z]{2}", message = "O estado deve ser informado com duas letras") String estado
) { }
