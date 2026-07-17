package br.com.atcare.ms_pessoa.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record PessoaFisicaRequest(
        @NotBlank @Size(max = 150) String nome,
        @NotBlank @Pattern(regexp = "\\d{11}", message = "O CPF deve conter 11 dígitos") String cpf,
        @NotNull @Past LocalDate dataNascimento,
        @Size(max = 150) String nomeSocial
) {}
