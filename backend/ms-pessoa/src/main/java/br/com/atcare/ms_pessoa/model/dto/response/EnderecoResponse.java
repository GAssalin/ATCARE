package br.com.atcare.ms_pessoa.model.dto.response;

import br.com.atcare.ms_pessoa.model.enums.TipoEndereco;

public record EnderecoResponse(Long id, PessoaResumoResponse pessoa, TipoEndereco tipo, String logradouro,
                               String numero, String complemento, String bairro, MunicipioResponse municipio,
                               String cep, boolean principal, AuditoriaResponse auditoria) {}
