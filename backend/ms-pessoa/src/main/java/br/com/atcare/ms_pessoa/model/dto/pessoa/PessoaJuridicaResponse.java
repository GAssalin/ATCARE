package br.com.atcare.ms_pessoa.model.dto.pessoa;

/**
 * DTO de retorno que representa uma Pessoa Jurídica completa.
 */
public record PessoaJuridicaResponse(
        Long id,
        String nome,
        String cnpj,
        String razaoSocial,
        String nomeFantasia
) {}
