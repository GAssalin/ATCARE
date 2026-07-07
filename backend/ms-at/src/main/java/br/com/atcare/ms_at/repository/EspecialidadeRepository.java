package br.com.atcare.ms_at.repository;

import br.com.atcare.ms_at.model.entity.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {
}
