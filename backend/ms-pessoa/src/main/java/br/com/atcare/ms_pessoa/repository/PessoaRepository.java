package br.com.atcare.ms_pessoa.repository;

import br.com.atcare.ms_pessoa.model.entity.Pessoa;
import br.com.atcare.ms_pessoa.model.enums.TipoPessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório responsável pelo acesso aos dados da entidade {@link Pessoa}.
 * <p>
 * Fornece operações CRUD padrão e consultas customizadas que permitem
 * localizar pessoas por nome, tipo ou outras classificações relevantes
 * dentro do ATCARE.
 */
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> { }
