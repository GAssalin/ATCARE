package br.com.atcare.ms_at.model.dto.request;

import br.com.atcare.ms_at.model.entity.TipoDocumento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DocumentoRequestDTO(
        Long id,
        @NotBlank(message = "O nome do documento é obrigatório")
        @Size(max = 150, message = "O nome do documento deve possuir no máximo 150 caracteres") String nome,
        @NotNull(message = "O tipo do documento é obrigatório") TipoDocumento tipo,
        @NotBlank(message = "O arquivo de comprovante é obrigatório")
        @Size(max = 500, message = "O arquivo de comprovante deve possuir no máximo 500 caracteres")
        String arquivoComprovante,
        @Size(max = 500, message = "A observação deve possuir no máximo 500 caracteres") String observacao
) { }
