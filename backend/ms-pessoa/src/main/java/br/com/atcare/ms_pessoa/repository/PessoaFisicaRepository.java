package br.com.atcare.ms_pessoa.repository;

import br.com.atcare.ms_pessoa.model.entity.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositório responsável pelo acesso aos dados da entidade {@link PessoaFisica}.
 * <p>
 * Permite consultas específicas relacionadas a pessoas físicas, como pesquisa por CPF,
 * nome social e filtros complementares utilizados nos módulos do ATCARE.
 */
@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long> { }
