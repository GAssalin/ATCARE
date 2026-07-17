package br.com.atcare.ms_pessoa.repository;

import br.com.atcare.ms_pessoa.model.entity.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByIdAndAtivoTrue(Long id);

    Optional<Usuario> findByEmailIgnoreCaseAndAtivoTrue(String email);

    Optional<Usuario> findByPessoaIdAndAtivoTrue(Long pessoaId);

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByPessoaId(Long pessoaId);
}
