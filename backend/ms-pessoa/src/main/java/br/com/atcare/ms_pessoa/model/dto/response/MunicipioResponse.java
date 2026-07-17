package br.com.atcare.ms_pessoa.model.dto.response;

import br.com.atcare.ms_pessoa.model.enums.Uf;

public record MunicipioResponse(Long id, String nome, Uf uf, String codigoIbge, AuditoriaResponse auditoria) {}
