package br.com.atcare.core.usuario.auth.dto;

public record UsuarioAuthResponse(
        Long userId,
        String email,
        String passwordHash
) {}
