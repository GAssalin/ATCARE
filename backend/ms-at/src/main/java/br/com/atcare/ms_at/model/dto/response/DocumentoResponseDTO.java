package br.com.atcare.ms_at.model.dto.response;

import br.com.atcare.ms_at.model.entity.TipoDocumento;

public record DocumentoResponseDTO(Long id, String nome, TipoDocumento tipo,
                                   String arquivoComprovante, String observacao) { }
