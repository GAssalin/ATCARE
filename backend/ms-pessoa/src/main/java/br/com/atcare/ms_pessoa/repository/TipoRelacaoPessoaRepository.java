package br.com.atcare.ms_pessoa.repository;

import br.com.atcare.ms_pessoa.model.entity.TipoRelacaoPessoa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoRelacaoPessoaRepository extends JpaRepository<TipoRelacaoPessoa, Long> {
    Optional<TipoRelacaoPessoa> findByIdAndAtivoTrue(Long id);

    Optional<TipoRelacaoPessoa> findByNomeIgnoreCaseAndAtivoTrue(String nome);

    List<TipoRelacaoPessoa> findAllByAtivoTrueOrderByNomeAsc();

    boolean existsByNomeIgnoreCase(String nome);
}
