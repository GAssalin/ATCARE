package br.com.atcare.ms_pessoa.repository;

import br.com.atcare.ms_pessoa.model.entity.Municipio;
import br.com.atcare.ms_pessoa.model.enums.Uf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositório responsável pelo acesso aos dados da entidade {@link Municipio}.
 * <p>
 * Oferece consultas utilizadas em cadastros de endereços, validações de CEP,
 * integrações geográficas e demais operações que dependem da localização.
 */
@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Long> { }
