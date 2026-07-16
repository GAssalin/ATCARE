package br.com.atcare.ms_at.model.dto.response;

public record LocalAtendimentoResponseDTO(Long id, EnderecoResponseDTO endereco,
                                          Boolean atendimentoDomiciliar, Boolean atendimentoOnline) { }
