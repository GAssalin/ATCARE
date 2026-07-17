package br.com.atcare.ms_pessoa.repository;

import br.com.atcare.ms_pessoa.model.entity.Municipio;
import br.com.atcare.ms_pessoa.model.enums.Uf;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MunicipioRepository extends JpaRepository<Municipio, Long> {
    Optional<Municipio> findByIdAndAtivoTrue(Long id);

    Optional<Municipio> findByCodigoIbgeAndAtivoTrue(String codigoIbge);

    Optional<Municipio> findByNomeIgnoreCaseAndUfAndAtivoTrue(String nome, Uf uf);

    List<Municipio> findAllByUfAndAtivoTrueOrderByNomeAsc(Uf uf);

    boolean existsByCodigoIbge(String codigoIbge);
}
