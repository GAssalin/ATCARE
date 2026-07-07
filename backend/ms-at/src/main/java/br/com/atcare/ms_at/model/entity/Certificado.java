package br.com.atcare.ms_at.model.entity;

import br.com.atcare.core.base.model.EntidadeAuditavel;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "certificado")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Certificado extends EntidadeAuditavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Curso curso;

    @Column(nullable = false)
    private String arquivo;

    @OneToMany(mappedBy = "certificado")
    private List<Validacao> validacoes;
}