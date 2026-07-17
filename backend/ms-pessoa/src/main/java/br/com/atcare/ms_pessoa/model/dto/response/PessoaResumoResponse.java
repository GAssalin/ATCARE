package br.com.atcare.ms_pessoa.model.dto.response;

import br.com.atcare.ms_pessoa.model.enums.TipoPessoa;

public record PessoaResumoResponse(Long id, String nome, TipoPessoa tipoPessoa) {}
