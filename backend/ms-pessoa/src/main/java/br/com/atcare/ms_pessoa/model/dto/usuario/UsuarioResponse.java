package br.com.atcare.ms_pessoa.model.dto.usuario;

import java.time.LocalDateTime;

/**
 * DTO utilizado para retornar informações completas sobre um usuário.
 */
public record UsuarioResponse(
        Long id,
        String login,
        Long pessoaId,
        String email,
        boolean emailVerificado,
        boolean ativo
) {}
