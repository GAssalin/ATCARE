package br.com.atcare.ms_pessoa.model.dto.response;

import java.time.LocalDateTime;

public record UsuarioEmailVerificacaoResponse(Long id, Long usuarioId, LocalDateTime expiracao,
                                               boolean utilizado, AuditoriaResponse auditoria) {}
