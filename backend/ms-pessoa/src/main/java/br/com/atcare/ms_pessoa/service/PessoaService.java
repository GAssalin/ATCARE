package br.com.atcare.ms_pessoa.service;

import br.com.atcare.ms_pessoa.model.dto.response.PessoaResumoResponse;
import br.com.atcare.ms_pessoa.model.entity.Pessoa;
import br.com.atcare.ms_pessoa.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaService {
    private final PessoaRepository repository;

    @Transactional
    public Pessoa salvar(Pessoa pessoa) {
        pessoa.setId(null);
        return repository.save(pessoa);
    }

    @Transactional
    public Pessoa atualizar(Long id, Pessoa dados) {
        Pessoa pessoa = buscarPorId(id);
        pessoa.setNome(dados.getNome());
        return repository.save(pessoa);
    }

    @Transactional(readOnly = true)
    public Pessoa buscarPorId(Long id) {
        return repository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada: " + id));
    }

    @Transactional(readOnly = true)
    public List<Pessoa> listar() {
        return repository.findAllByAtivoTrue(Pageable.unpaged()).getContent();
    }

    @Transactional(readOnly = true)
    public Page<Pessoa> listar(String nome, Pageable pageable) {
        return nome == null || nome.isBlank()
                ? repository.findAllByAtivoTrue(pageable)
                : repository.findByNomeContainingIgnoreCaseAndAtivoTrue(nome, pageable);
    }

    @Transactional
    public void deletar(Long id) {
        Pessoa pessoa = buscarPorId(id);
        pessoa.setAtivo(false);
        repository.save(pessoa);
    }

    public PessoaResumoResponse buscarResumoPorId(Long id) {
         Pessoa p = buscarPorId(id);
         return new PessoaResumoResponse(p.getId(), p.getNome(), p.getTipoPessoa());
    }

    public List<PessoaResumoResponse> listarResumos(String nome, Pageable pageable) {
        return listar(nome, pageable)
                .map(p -> new PessoaResumoResponse(p.getId(), p.getNome(), p.getTipoPessoa()))
                .stream().toList();
    }
}
