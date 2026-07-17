package br.com.atcare.ms_pessoa.model.dto.response;

import java.time.LocalDateTime;

public record AuditoriaResponse(
        Long criadoPor,
        LocalDateTime criadoEm,
        Long atualizadoPor,
        LocalDateTime atualizadoEm,
        Boolean ativo
) {}
