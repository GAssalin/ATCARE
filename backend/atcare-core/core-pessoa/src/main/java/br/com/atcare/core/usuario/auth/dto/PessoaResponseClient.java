package br.com.atcare.core.usuario.auth.dto;

/**
 * DTO de retorno que representa os dados básicos de uma pessoa.
 * <p>
 * Pode ser utilizado como base em respostas para Pessoa Física e Jurídica.
 */
public record PessoaResponseClient(
        Long id,
        String nome
) {}
