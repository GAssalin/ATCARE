package br.com.atcare.ms_pessoa.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UsuarioEmailVerificacaoRequest(@NotBlank String token) {}
