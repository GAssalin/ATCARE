package br.com.atcare.ms_pessoa.repository;

import br.com.atcare.ms_pessoa.model.entity.UsuarioEmailVerificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório responsável pelo acesso aos dados relacionados ao processo de
 * verificação de e-mail do usuário.
 *
 * <p>
 * Gerencia tokens temporários utilizados para confirmar o endereço de e-mail
 * durante o fluxo de criação de conta, bem como seus prazos de validade.
 * </p>
 */
@Repository
public interface UsuarioEmailVerificacaoRepository extends JpaRepository<UsuarioEmailVerificacao, Long> {

}
