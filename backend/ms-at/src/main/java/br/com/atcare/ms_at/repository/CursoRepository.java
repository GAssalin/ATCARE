package br.com.atcare.ms_at.repository;

import br.com.atcare.ms_at.model.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
