package br.com.atcare.ms_pessoa.model.dto.pessoa;

/**
 * DTO de retorno que representa os dados básicos de uma pessoa.
 * <p>
 * Pode ser utilizado como base em respostas para Pessoa Física e Jurídica.
 */
public record PessoaResponse(
        Long id,
        String nome,
        String tipoPessoa
) {}
