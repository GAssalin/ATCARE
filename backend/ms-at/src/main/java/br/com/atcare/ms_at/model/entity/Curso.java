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

    @ManyToOne(optional = false)
    private Formacao formacao;

    @Column(nullable = false)
    private String nome;

    @Column(nullable =false)
    private String instituicao;

    private Integer cargaHoraria;

    @OneToMany(mappedBy = "curso")
    private List<Certificado> certificados;
}