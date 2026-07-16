package br.com.atcare.ms_at.model.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CursoRequestDTO(
        Long id,
        @NotBlank(message = "O nome do curso é obrigatório")
        @Size(max = 200, message = "O nome do curso deve possuir no máximo 200 caracteres") String nome,
        @NotBlank(message = "A instituição do curso é obrigatória")
        @Size(max = 200, message = "A instituição deve possuir no máximo 200 caracteres") String instituicao,
        @NotNull(message = "A carga horária é obrigatória")
        @Min(value = 1, message = "A carga horária deve ser maior que zero") Integer cargaHoraria,
        @Valid @NotEmpty(message = "Informe ao menos um certificado para o curso")
        List<CertificadoRequestDTO> certificados
) { }
