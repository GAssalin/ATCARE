package br.com.atcare.ms_pessoa.model.dto.usuario;

/**
 * DTO utilizado para criação ou atualização de usuários.
 */
public record UsuarioRequest(
        Long pessoaId,
        String password,
        String email,
        Boolean ativo
) {}
