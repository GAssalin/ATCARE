package br.com.atcare.ms_pessoa.model.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO utilizado para criação ou atualização de usuários.
 */
public record UsuarioRequest(
        @NotBlank String login,
        Long pessoaId,
        @NotBlank String password,
        String email,
        Boolean ativo,
        @NotNull Long roleId
) {}
