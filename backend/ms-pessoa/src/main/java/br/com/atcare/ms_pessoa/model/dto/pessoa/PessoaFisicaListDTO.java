package br.com.atcare.ms_pessoa.model.dto.pessoa;

/**
 * DTO utilizado em listagens de Pessoas Físicas,
 * trazendo apenas informações essenciais.
 */
public record PessoaFisicaListDTO(
        Long id,
        String nome,
        String cpf
) {}
