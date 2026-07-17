package br.com.atcare.ms_pessoa.model.dto.response;

import br.com.atcare.ms_pessoa.model.enums.TipoDocumento;
import java.time.LocalDate;

public record DocumentoResponse(Long id, PessoaResumoResponse pessoa, TipoDocumento tipo, String numero,
                                String orgaoEmissor, LocalDate dataEmissao, AuditoriaResponse auditoria) {}
