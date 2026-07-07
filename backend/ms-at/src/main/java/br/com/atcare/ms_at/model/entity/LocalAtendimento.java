package br.com.atcare.ms_at.model.entity;

import br.com.atcare.core.base.model.EntidadeAuditavel;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "local_atendimento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class LocalAtendimento extends EntidadeAuditavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private At at;

    @ManyToOne
    private Endereco endereco;

    @Column(nullable = false)
    private Boolean atendimentoDomiciliar;

    @Column(nullable = false)
    private Boolean atendimentoOnline;
}