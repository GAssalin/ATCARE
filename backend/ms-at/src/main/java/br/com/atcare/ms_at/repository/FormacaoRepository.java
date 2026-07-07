package br.com.atcare.ms_at.repository;

import br.com.atcare.ms_at.model.entity.Formacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormacaoRepository extends JpaRepository<Formacao, Long> {
}
