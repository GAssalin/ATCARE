package br.com.atcare.ms_pessoa.model.entity;

import br.com.atcare.core.base.model.EntidadeAuditavel;
import br.com.atcare.ms_pessoa.model.enums.TipoPessoa;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Representa uma pessoa de forma genérica dentro do ATCARE.
 * <p>
 * Esta classe é a base para Pessoa Física e Pessoa Jurídica,
 * armazenando informações comuns como nome e tipo.
 * <p>
 * Utiliza estratégia de herança JOINED para garantir normalização,
 * mantendo tabelas separadas e coerentes com modelos corporativos.
 */
@Entity
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class Pessoa extends EntidadeAuditavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome principal ou razão social simplificada.
     */
    @NotBlank
    @Column(nullable = false, length = 150)
    private String nome;

    /**
     * Define se a pessoa é física ou jurídica.
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoPessoa tipoPessoa;
}
