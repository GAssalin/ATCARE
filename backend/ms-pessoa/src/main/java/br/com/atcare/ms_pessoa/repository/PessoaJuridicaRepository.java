package br.com.atcare.ms_pessoa.repository;

import br.com.atcare.ms_pessoa.model.entity.PessoaJuridica;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long> {
    Optional<PessoaJuridica> findByIdAndAtivoTrue(Long id);

    Optional<PessoaJuridica> findByCnpjAndAtivoTrue(String cnpj);

    boolean existsByCnpj(String cnpj);

    Page<PessoaJuridica> findAllByAtivoTrue(Pageable pageable);

    Page<PessoaJuridica> findByRazaoSocialContainingIgnoreCaseAndAtivoTrue(String razaoSocial, Pageable pageable);
}
