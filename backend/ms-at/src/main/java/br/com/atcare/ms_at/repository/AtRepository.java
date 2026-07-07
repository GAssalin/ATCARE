package br.com.atcare.ms_at.repository;

import br.com.atcare.ms_at.model.entity.At;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtRepository extends JpaRepository<At, Long> {
}
