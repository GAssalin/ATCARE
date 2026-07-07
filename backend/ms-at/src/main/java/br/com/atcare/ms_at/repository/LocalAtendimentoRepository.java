package br.com.atcare.ms_at.repository;

import br.com.atcare.ms_at.model.entity.LocalAtendimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalAtendimentoRepository extends JpaRepository<LocalAtendimento, Long> {
}
