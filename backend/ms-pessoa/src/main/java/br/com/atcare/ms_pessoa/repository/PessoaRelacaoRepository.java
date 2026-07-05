package br.com.atcare.ms_pessoa.repository;

import br.com.atcare.ms_pessoa.model.entity.Pessoa;
import br.com.atcare.ms_pessoa.model.entity.PessoaRelacao;
import br.com.atcare.ms_pessoa.model.entity.TipoRelacaoPessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório responsável pelo acesso aos dados da entidade {@link PessoaRelacao}.
 * <p>
 * Permite a consulta de vínculos entre pessoas, essenciais para processos
 * corporativos como identificação de responsáveis, dependentes, sócios,
 * representantes legais e outras relações definidas dentro do ATCARE.
 */
@Repository
public interface PessoaRelacaoRepository extends JpaRepository<PessoaRelacao, Long> { }
