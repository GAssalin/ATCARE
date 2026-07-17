package br.com.atcare.ms_pessoa.model.dto.response;

import br.com.atcare.ms_pessoa.model.enums.TipoPessoa;

public record PessoaJuridicaResponse(Long id, String nome, TipoPessoa tipoPessoa, String cnpj,
                                     String razaoSocial, String nomeFantasia, AuditoriaResponse auditoria) {}
