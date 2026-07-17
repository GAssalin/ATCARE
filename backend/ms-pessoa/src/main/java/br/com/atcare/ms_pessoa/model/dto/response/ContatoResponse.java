package br.com.atcare.ms_pessoa.model.dto.response;

import br.com.atcare.ms_pessoa.model.enums.TipoContato;

public record ContatoResponse(Long id, PessoaResumoResponse pessoa, TipoContato tipo, String valor,
                              boolean principal, AuditoriaResponse auditoria) {}
