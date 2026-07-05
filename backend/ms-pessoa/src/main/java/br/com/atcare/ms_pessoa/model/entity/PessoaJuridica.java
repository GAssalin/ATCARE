package br.com.atcare.ms_pessoa.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Representa uma pessoa jurídica cadastrada no ATCARE.
 * <p>
 * Armazena informações essenciais como CNPJ, razão social e nome fantasia,
 * possibilitando integração com módulos financeiros, fiscais e de contratos.
 */
@Entity
@Table(name = "pessoa_juridica")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PessoaJuridica extends Pessoa {

    /**
     * CNPJ da empresa sem formatação.
     */
    @NotBlank
    @Column(nullable = false, unique = true, length = 14)
    private String cnpj;

    /**
     * Razão social completa.
     */
    @NotBlank
    @Column(name = "razao_social", nullable = false, length = 200)
    private String razaoSocial;

    /**
     * Nome fantasia utilizado comercialmente.
     */
    @Column(name = "nome_fantasia", length = 200)
    private String nomeFantasia;
}
