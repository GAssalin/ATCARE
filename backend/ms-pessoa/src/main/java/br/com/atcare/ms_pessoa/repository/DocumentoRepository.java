package br.com.atcare.ms_pessoa.repository;

import br.com.atcare.ms_pessoa.model.entity.Documento;
import br.com.atcare.ms_pessoa.model.entity.Pessoa;
import br.com.atcare.ms_pessoa.model.enums.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositório responsável pelo acesso aos dados da entidade {@link Documento}.
 */
@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> { }
