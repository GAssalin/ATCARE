package br.com.atcare.ms_pessoa.repository;

import br.com.atcare.ms_pessoa.model.entity.UsuarioEmailVerificacao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioEmailVerificacaoRepository extends JpaRepository<UsuarioEmailVerificacao, Long> {
    Optional<UsuarioEmailVerificacao> findByTokenHashAndUtilizadoFalseAndAtivoTrue(String tokenHash);

    Optional<UsuarioEmailVerificacao> findFirstByUsuarioIdAndUtilizadoFalseAndAtivoTrueOrderByExpiracaoDesc(
            Long usuarioId);

    List<UsuarioEmailVerificacao> findAllByUtilizadoFalseAndExpiracaoBefore(LocalDateTime dataHora);

    boolean existsByTokenHash(String tokenHash);
}
