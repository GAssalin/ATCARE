package br.com.atcare.ms_pessoa.repository;

import br.com.atcare.ms_pessoa.model.entity.Contato;
import br.com.atcare.ms_pessoa.model.entity.Pessoa;
import br.com.atcare.ms_pessoa.model.enums.TipoContato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório responsável pelo acesso aos dados da entidade {@link Contato}.
 * <p>
 * Centraliza consultas relacionadas aos meios de contato de uma pessoa,
 * respeitando o contexto multi-tenant (empresa).
 * </p>
 *
 * <p>
 * Este repositório é amplamente utilizado em fluxos de:
 * <ul>
 *     <li>Cadastro e manutenção de pessoas</li>
 *     <li>Definição de contato principal</li>
 *     <li>Processos de autenticação e notificação</li>
 * </ul>
 * </p>
 */
@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> { }
