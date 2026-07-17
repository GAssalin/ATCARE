package br.com.atcare.ms_pessoa.repository;

import br.com.atcare.ms_pessoa.model.entity.Documento;
import br.com.atcare.ms_pessoa.model.enums.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {
    Optional<Documento> findByIdAndAtivoTrue(Long id);

    Optional<Documento> findByTipoAndNumeroAndAtivoTrue(TipoDocumento tipo, String numero);

    List<Documento> findAllByPessoaIdAndAtivoTrue(Long pessoaId);

    boolean existsByTipoAndNumero(TipoDocumento tipo, String numero);
}
