package br.com.atcare.ms_pessoa.repository;

import br.com.atcare.ms_pessoa.model.entity.Contato;
import br.com.atcare.ms_pessoa.model.enums.TipoContato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
    Optional<Contato> findByIdAndAtivoTrue(Long id);

    List<Contato> findAllByPessoaIdAndAtivoTrue(Long pessoaId);

    Optional<Contato> findFirstByPessoaIdAndPrincipalTrueAndAtivoTrue(Long pessoaId);

    boolean existsByPessoaIdAndTipoAndValorAndAtivoTrue(Long pessoaId, TipoContato tipo, String valor);
}
