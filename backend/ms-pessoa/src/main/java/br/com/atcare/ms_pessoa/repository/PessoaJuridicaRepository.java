package br.com.atcare.ms_pessoa.repository;

import br.com.atcare.ms_pessoa.model.entity.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositório responsável pelo acesso aos dados da entidade {@link PessoaJuridica}.
 */
@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long> { }
