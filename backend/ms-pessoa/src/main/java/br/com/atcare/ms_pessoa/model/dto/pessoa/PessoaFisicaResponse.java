package br.com.atcare.ms_pessoa.model.dto.pessoa;

import java.time.LocalDate;

/**
 * DTO de retorno que representa uma Pessoa Física completa.
 */
public record PessoaFisicaResponse(
        Long id,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        String nomeSocial
) {}
