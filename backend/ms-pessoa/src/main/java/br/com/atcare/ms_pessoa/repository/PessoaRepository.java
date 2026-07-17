package br.com.atcare.ms_pessoa.repository;

import br.com.atcare.ms_pessoa.model.entity.Pessoa;
import br.com.atcare.ms_pessoa.model.enums.TipoPessoa;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Optional<Pessoa> findByIdAndAtivoTrue(Long id);

    Page<Pessoa> findAllByAtivoTrue(Pageable pageable);

    Page<Pessoa> findByNomeContainingIgnoreCaseAndAtivoTrue(String nome, Pageable pageable);

    Page<Pessoa> findByTipoPessoaAndAtivoTrue(TipoPessoa tipoPessoa, Pageable pageable);
}
