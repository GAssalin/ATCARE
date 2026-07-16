package br.com.atcare.ms_at.model.dto.response;

import java.util.List;

public record AtResponseDTO(Long id, Long pessoaId, List<FormacaoResponseDTO> formacoes,
                            List<LocalAtendimentoResponseDTO> locaisAtendimento,
                            List<AtEspecialidadeResponseDTO> especialidades,
                            List<DocumentoResponseDTO> documentos) { }
