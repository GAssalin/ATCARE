package br.com.atcare.ms_pessoa.model.dto.pessoa;

/**
 * DTO utilizado em listagens de Pessoas Jurídicas,
 * trazendo apenas informações essenciais.
 */
public record PessoaJuridicaListDTO(
        Long id,
        String nome,
        String cnpj
) {}
