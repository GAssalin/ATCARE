package br.com.atcare.ms_at.model.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record FormacaoRequestDTO(
        Long id,
        @NotBlank(message = "A instituição da formação é obrigatória")
        @Size(max = 200, message = "A instituição deve possuir no máximo 200 caracteres") String instituicao,
        @NotBlank(message = "O título da formação é obrigatório")
        @Size(max = 150, message = "O título deve possuir no máximo 150 caracteres") String titulo,
        @Min(value = 1900, message = "O ano de conclusão deve ser igual ou superior a 1900")
        @Max(value = 2100, message = "O ano de conclusão deve ser igual ou inferior a 2100") Integer anoConclusao,
        @Valid @NotEmpty(message = "Informe ao menos um curso para a formação") List<CursoRequestDTO> cursos
) { }
