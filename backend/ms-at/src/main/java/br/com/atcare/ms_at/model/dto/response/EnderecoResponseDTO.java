package br.com.atcare.ms_at.model.dto.response;

public record EnderecoResponseDTO(Long id, String cep, String logradouro, String numero,
                                  String complemento, String bairro, String cidade, String estado) { }
