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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "at_id", nullable = false)
    private At at;

    @Column(name = "instituicao", nullable = false, length = 200)
    private String instituicao;

    @Column(name = "titulo", nullable = false, length = 150)
    private String titulo;

    @Column(name = "ano_conclusao")
    private Integer anoConclusao;

    @OneToMany(mappedBy = "formacao")
    private List<Curso> cursos;
}
