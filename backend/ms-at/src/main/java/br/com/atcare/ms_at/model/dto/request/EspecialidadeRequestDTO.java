package br.com.atcare.ms_at.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EspecialidadeRequestDTO(
        Long id,
        @NotBlank(message = "O nome da especialidade é obrigatório")
        @Size(max = 150, message = "O nome da especialidade deve possuir no máximo 150 caracteres") String nome,
        @Size(max = 500, message = "A descrição deve possuir no máximo 500 caracteres") String descricao
) { }
