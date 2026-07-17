package br.com.atcare.ms_pessoa.repository;

import br.com.atcare.ms_pessoa.model.entity.PessoaRelacao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRelacaoRepository extends JpaRepository<PessoaRelacao, Long> {
    Optional<PessoaRelacao> findByIdAndAtivoTrue(Long id);

    List<PessoaRelacao> findAllByPessoaIdAndAtivoTrue(Long pessoaId);

    List<PessoaRelacao> findAllByRelacionadoIdAndAtivoTrue(Long relacionadoId);

    boolean existsByPessoaIdAndRelacionadoIdAndTipoRelacaoIdAndAtivoTrue(
            Long pessoaId, Long relacionadoId, Long tipoRelacaoId);
}
