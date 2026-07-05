package br.com.atcare.ms_pessoa.model.dto.pessoa;

/**
 * DTO utilizado para listagem de pessoas,
 * apresentando informações essenciais para consultas rápidas.
 */
public record PessoaListDTO(
        Long id,
        String nome,
        String tipoPessoa
) {}
