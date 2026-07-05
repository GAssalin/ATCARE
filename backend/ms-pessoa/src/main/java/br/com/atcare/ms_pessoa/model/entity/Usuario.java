package br.com.atcare.ms_pessoa.model.entity;

import br.com.atcare.core.base.model.EntidadeAuditavel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * Representa um usuário autenticável do sistema.
 *
 * <p>
 * Este microserviço é responsável apenas pelos dados cadastrais
 * do usuário. Informações de autenticação e permissões são compartilhadas com os serviços de identidade. A role do usuário é armazenada neste microserviço por meio do campo roleId.
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

    @NotBlank
    @Size(min = 4, max = 100)
    @Column(nullable = false, unique = true)
    private String login;

    @Column(name = "pessoa_id")
    private Long pessoaId;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @NotBlank
    @Email
    @Column(length = 150, nullable = false, unique = true)
    private String email;

    @Column(name = "role_id", nullable = false)
    private Long roleId;

    @Builder.Default
    @Column(name = "email_verificado", nullable = false)
    private boolean emailVerificado = false;

    @Column(name = "refresh_token", length = 200)
    private String refreshToken;

    @Column(name = "expiracao_refresh_token")
    private LocalDateTime expiracaoRefreshToken;

}
