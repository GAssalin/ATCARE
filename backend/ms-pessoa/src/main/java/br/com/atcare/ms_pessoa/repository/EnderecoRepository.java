package br.com.atcare.ms_pessoa.repository;

import br.com.atcare.ms_pessoa.model.entity.Endereco;
import br.com.atcare.ms_pessoa.model.enums.TipoEndereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    Optional<Endereco> findByIdAndAtivoTrue(Long id);

    List<Endereco> findAllByPessoaIdAndAtivoTrue(Long pessoaId);

    List<Endereco> findAllByPessoaIdAndTipoAndAtivoTrue(Long pessoaId, TipoEndereco tipo);

    Optional<Endereco> findFirstByPessoaIdAndPrincipalTrueAndAtivoTrue(Long pessoaId);
}
