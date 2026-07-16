package br.com.atcare.ms_at.repository;

import br.com.atcare.ms_at.model.entity.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtDocumentoRepository extends JpaRepository<Documento, Long> {
}
