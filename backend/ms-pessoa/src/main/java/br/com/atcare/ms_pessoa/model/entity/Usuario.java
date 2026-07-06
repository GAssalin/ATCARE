package br.com.atcare.ms_pessoa.model.entity;

import br.com.atcare.core.base.model.EntidadeAuditavel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Representa um usuário autenticável do sistema.
 *
 * <p>
 * Este microserviço é responsável apenas pelos dados cadastrais
 * do usuário. Informações de autenticação e permissões são compartilhadas com os serviços de identidade.
 * </p>
 */
@Entity
@Table(name = "usuario")
@Getter
@Setter
@ToString(exclude = "password")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Usuario extends EntidadeAuditavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pessoa_id")
    private Long pessoaId;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @NotBlank
    @Email
    @Column(length = 150, nullable = false, unique = true)
    private String email;

    @Builder.Default
    @Column(name = "email_verificado", nullable = false)
    private boolean emailVerificado = false;

}
