package br.com.atcare.ms_pessoa.model.dto.response;

public record PessoaRelacaoResponse(Long id, PessoaResumoResponse pessoa, PessoaResumoResponse relacionado,
                                    TipoRelacaoPessoaResponse tipoRelacao, AuditoriaResponse auditoria) {}
