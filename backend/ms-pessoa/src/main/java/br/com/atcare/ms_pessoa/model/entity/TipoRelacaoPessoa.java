package br.com.atcare.ms_pessoa.model.entity;

import br.com.atcare.core.base.model.EntidadeAuditavel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Representa um tipo de relacionamento possível entre pessoas dentro do ATCARE.
 * <p>
 * Exemplos comuns incluem: Pai, Mãe, Dependente, Sócio, Representante Legal,
 * responsável financeiro, entre outros. Esta tabela permite flexibilidade
 * para que o administrador inclua novos tipos sem alterações no código.
 */
@Entity
@Table(name = "tipo_relacao_pessoa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class TipoRelacaoPessoa extends EntidadeAuditavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome do tipo de relação (ex: "Pai", "Sócio", "Responsável Legal").
     */
    @NotBlank
    @Column(nullable = false, unique = true, length = 100)
    private String nome;

    /**
     * Descrição detalhada da relação, quando necessário.
     */
    @Column(length = 255)
    private String descricao;
}
