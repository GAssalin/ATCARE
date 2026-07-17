package br.com.atcare.ms_pessoa.repository;

import br.com.atcare.ms_pessoa.model.entity.PessoaFisica;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long> {
    Optional<PessoaFisica> findByIdAndAtivoTrue(Long id);

    Optional<PessoaFisica> findByCpfAndAtivoTrue(String cpf);

    boolean existsByCpf(String cpf);

    Page<PessoaFisica> findAllByAtivoTrue(Pageable pageable);

    Page<PessoaFisica> findByNomeContainingIgnoreCaseAndAtivoTrue(String nome, Pageable pageable);
}
