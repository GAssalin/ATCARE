package br.com.atcare.ms_pessoa.repository;

import br.com.atcare.ms_pessoa.model.entity.TipoRelacaoPessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositório responsável pelo acesso aos dados da entidade {@link TipoRelacaoPessoa}.
 * <p>
 * Permite o gerenciamento dos tipos de vínculos possíveis entre pessoas,
 * utilizados em cadastros corporativos, processos contratuais, dependentes,
 * representantes legais e demais relações definidas dentro do ATCARE.
 */
@Repository
public interface TipoRelacaoPessoaRepository extends JpaRepository<TipoRelacaoPessoa, Long> { }
