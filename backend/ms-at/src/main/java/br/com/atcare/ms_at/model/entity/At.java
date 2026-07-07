package br.com.atcare.ms_at.model.entity;

import br.com.atcare.core.base.model.EntidadeAuditavel;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "at")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class At extends EntidadeAuditavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long pessoaId;

    @OneToMany(mappedBy = "at")
    private List<Formacao> formacoes;

    @OneToMany(mappedBy = "at")
    private List<LocalAtendimento> locaisAtendimento;

    @OneToMany(mappedBy = "at")
    private List<AtEspecialidade> especialidades;
}