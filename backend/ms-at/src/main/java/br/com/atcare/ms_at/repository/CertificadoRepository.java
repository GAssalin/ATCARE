package br.com.atcare.ms_at.repository;

import br.com.atcare.ms_at.model.entity.Certificado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificadoRepository extends JpaRepository<Certificado, Long> {
}
