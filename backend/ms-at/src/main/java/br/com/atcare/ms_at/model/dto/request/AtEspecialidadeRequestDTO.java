package br.com.atcare.ms_at.model.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AtEspecialidadeRequestDTO(
        Long id,
        @NotNull(message = "O identificador da especialidade é obrigatório")
        @Positive(message = "O identificador da especialidade deve ser maior que zero") Long especialidadeId
) { }
