package br.com.atcare.ms_at.model.entity;

import br.com.atcare.core.base.model.EntidadeAuditavel;
import br.com.atcare.ms_at.model.enums.StatusValidacao;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "validacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Validacao extends EntidadeAuditavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "certificado_id", nullable = false)
    private Certificado certificado;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private StatusValidacao status;

    @Column(name = "observacao", length = 500)
    private String observacao;

    @Column(name = "data_validacao")
    private LocalDateTime dataValidacao;

    @Column(name = "usuario_validador")
    private Long usuarioValidador;
}
