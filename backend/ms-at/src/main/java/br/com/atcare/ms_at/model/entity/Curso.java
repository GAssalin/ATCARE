package br.com.atcare.ms_at.model.entity;

import br.com.atcare.core.base.model.EntidadeAuditavel;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "curso")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Curso extends EntidadeAuditavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "formacao_id", nullable = false)
    private Formacao formacao;

    @Column(name = "nome", nullable = false, length = 200)
    private String nome;

    @Column(name = "instituicao", nullable = false, length = 200)
    private String instituicao;

    @Column(name = "carga_horaria", nullable = false)
    private Integer cargaHoraria;

    @OneToMany(mappedBy = "curso")
    private List<Certificado> certificados;
}
