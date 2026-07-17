package br.com.atcare.ms_pessoa.model.dto.response;

import br.com.atcare.ms_pessoa.model.enums.TipoPessoa;
import java.time.LocalDate;

public record PessoaFisicaResponse(Long id, String nome, TipoPessoa tipoPessoa, String cpf,
                                   LocalDate dataNascimento, String nomeSocial, AuditoriaResponse auditoria) {}
