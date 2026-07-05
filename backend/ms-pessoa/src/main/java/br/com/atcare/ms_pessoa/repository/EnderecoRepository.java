package br.com.atcare.ms_pessoa.repository;

import br.com.atcare.ms_pessoa.model.entity.Endereco;
import br.com.atcare.ms_pessoa.model.entity.Municipio;
import br.com.atcare.ms_pessoa.model.entity.Pessoa;
import br.com.atcare.ms_pessoa.model.enums.TipoEndereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositório responsável pelo acesso aos dados da entidade {@link Endereco}.
 * <p>
 * Oferece consultas específicas para gerenciamento de endereços relacionados
 * a pessoas físicas e jurídicas, incluindo filtros por tipo, município e
 * identificação do endereço principal.
 */
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> { }
