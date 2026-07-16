package br.com.atcare.ms_at.model.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record AtRequestDTO(
        Long id,
        @NotNull(message = "O identificador da pessoa é obrigatório")
        @Positive(message = "O identificador da pessoa deve ser maior que zero") Long pessoaId,
        @Valid @NotEmpty(message = "Informe ao menos uma formação") List<FormacaoRequestDTO> formacoes,
        @Valid List<LocalAtendimentoRequestDTO> locaisAtendimento,
        @Valid List<AtEspecialidadeRequestDTO> especialidades,
        @Valid List<DocumentoRequestDTO> documentos
) { }
