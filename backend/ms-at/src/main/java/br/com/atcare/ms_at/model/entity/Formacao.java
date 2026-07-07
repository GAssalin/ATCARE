package br.com.atcare.ms_at.model.entity;

import br.com.atcare.core.base.model.EntidadeAuditavel;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "formacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Formacao extends EntidadeAuditavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private At at;

    @Column(nullable = false)
    private String instituicao;

    @Column(nullable = false)
    private String titulo;

    private Integer anoConclusao;

    @OneToMany(mappedBy = "formacao")
    private List<Curso> cursos;
}