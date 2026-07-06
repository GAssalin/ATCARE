package br.com.atcare.ms_pessoa.model.dto.usuario;

/**
 * DTO resumido para listagem de usuários.
 */
public record UsuarioListDTO(
        Long id,
        String email,
        boolean emailVerificado,
        boolean ativo
) {}
