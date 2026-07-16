package br.com.atcare.ms_at.model.dto.response;

import br.com.atcare.ms_at.model.entity.StatusValidacao;

import java.time.LocalDateTime;

public record ValidacaoResponseDTO(Long id, StatusValidacao status, String observacao,
                                   LocalDateTime dataValidacao, Long usuarioValidador) { }
