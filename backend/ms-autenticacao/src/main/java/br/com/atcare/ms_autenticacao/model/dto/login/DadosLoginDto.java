package br.com.atcare.ms_autenticacao.model.dto.login;

import jakarta.validation.constraints.NotBlank;

public record DadosLoginDto(
        @NotBlank String email,
        @NotBlank String senha
) {}
