package br.com.atcare.ms_at.model.dto.response;

import java.util.List;

public record CertificadoResponseDTO(Long id, String arquivo, List<ValidacaoResponseDTO> validacoes) { }
