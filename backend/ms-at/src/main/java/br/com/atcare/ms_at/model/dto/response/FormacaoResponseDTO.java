package br.com.atcare.ms_at.model.dto.response;

import java.util.List;

public record FormacaoResponseDTO(Long id, String instituicao, String titulo, Integer anoConclusao,
                                  List<CursoResponseDTO> cursos) { }
