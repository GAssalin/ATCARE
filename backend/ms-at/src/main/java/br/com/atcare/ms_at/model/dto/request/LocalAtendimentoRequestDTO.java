package br.com.atcare.ms_at.model.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;

public record LocalAtendimentoRequestDTO(
        Long id,
        @Valid EnderecoRequestDTO endereco,
        @NotNull(message = "Informe se realiza atendimento domiciliar") Boolean atendimentoDomiciliar,
        @NotNull(message = "Informe se realiza atendimento online") Boolean atendimentoOnline
) {
    @AssertTrue(message = "Informe ao menos uma modalidade de atendimento")
    public boolean isModalidadeValida() {
        return Boolean.TRUE.equals(atendimentoDomiciliar) || Boolean.TRUE.equals(atendimentoOnline);
    }

    @AssertTrue(message = "O endereço é obrigatório para atendimento domiciliar")
    public boolean isEnderecoValido() {
        return !Boolean.TRUE.equals(atendimentoDomiciliar) || endereco != null;
    }
}
