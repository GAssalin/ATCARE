package br.com.atcare.ms_at.repository;

import br.com.atcare.ms_at.model.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
