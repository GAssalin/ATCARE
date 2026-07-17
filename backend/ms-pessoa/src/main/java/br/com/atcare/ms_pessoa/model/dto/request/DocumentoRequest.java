package br.com.atcare.ms_pessoa.model.dto.request;

import br.com.atcare.ms_pessoa.model.enums.TipoDocumento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record DocumentoRequest(
        @NotNull Long pessoaId,
        @NotNull TipoDocumento tipo,
        @NotBlank @Size(max = 50) String numero,
        @Size(max = 50) String orgaoEmissor,
        LocalDate dataEmissao
) {}
