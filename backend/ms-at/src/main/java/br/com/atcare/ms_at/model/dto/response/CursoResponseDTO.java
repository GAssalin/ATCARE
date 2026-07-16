package br.com.atcare.ms_at.model.dto.response;

import java.util.List;

public record CursoResponseDTO(Long id, String nome, String instituicao, Integer cargaHoraria,
                               List<CertificadoResponseDTO> certificados) { }
