package br.com.atcare.ms_at.model.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CertificadoRequestDTO(
        Long id,
        @NotBlank(message = "O arquivo do certificado é obrigatório")
        @Size(max = 500, message = "O arquivo deve possuir no máximo 500 caracteres") String arquivo,
        @Valid List<ValidacaoRequestDTO> validacoes
) { }
