package br.com.atcare.ms_at.model.dto.request;

import br.com.atcare.ms_at.model.entity.StatusValidacao;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record ValidacaoRequestDTO(
        Long id,
        @NotNull(message = "O status da validação é obrigatório") StatusValidacao status,
        @Size(max = 500, message = "A observação deve possuir no máximo 500 caracteres") String observacao,
        LocalDateTime dataValidacao,
        @Positive(message = "O identificador do usuário validador deve ser maior que zero") Long usuarioValidador
) { }
