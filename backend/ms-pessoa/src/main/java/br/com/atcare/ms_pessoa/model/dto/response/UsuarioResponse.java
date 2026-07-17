package br.com.atcare.ms_pessoa.model.dto.response;

public record UsuarioResponse(Long id, Long pessoaId, String email, boolean emailVerificado,
                              AuditoriaResponse auditoria) {}
