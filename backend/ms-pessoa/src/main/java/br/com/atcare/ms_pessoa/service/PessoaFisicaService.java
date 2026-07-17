package br.com.atcare.ms_pessoa.service;

import br.com.atcare.ms_pessoa.mapper.PessoaFisicaMapper;
import br.com.atcare.ms_pessoa.model.dto.request.PessoaFisicaRequest;
import br.com.atcare.ms_pessoa.model.dto.response.PessoaFisicaResponse;
import br.com.atcare.ms_pessoa.model.entity.PessoaFisica;
import br.com.atcare.ms_pessoa.repository.PessoaFisicaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaFisicaService {
    private final PessoaFisicaRepository repository;
    private final PessoaFisicaMapper mapper;

    @Transactional
    public PessoaFisicaResponse salvar(PessoaFisicaRequest request) {
        validarCpfDisponivel(request.cpf(), null);
        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    @Transactional
    public PessoaFisicaResponse atualizar(Long id, PessoaFisicaRequest request) {
        PessoaFisica pessoa = buscarPorId(id);
        validarCpfDisponivel(request.cpf(), pessoa.getCpf());
        mapper.updateEntity(pessoa, request);
        return mapper.toResponse(repository.save(pessoa));
    }

    @Transactional(readOnly = true)
    public Page<PessoaFisicaResponse> listar(String nome, Pageable pageable) {
        Page<PessoaFisica> pagina = nome == null || nome.isBlank()
                ? repository.findAllByAtivoTrue(pageable)
                : repository.findByNomeContainingIgnoreCaseAndAtivoTrue(nome, pageable);
        return pagina.map(mapper::toResponse);
    }

    @Transactional
    public PessoaFisica salvar(PessoaFisica pessoa) {
        validarCpfDisponivel(pessoa.getCpf(), null);
        pessoa.setId(null);
        return repository.save(pessoa);
    }

    @Transactional
    public PessoaFisica atualizar(Long id, PessoaFisica dados) {
        PessoaFisica pessoa = buscarPorId(id);
        validarCpfDisponivel(dados.getCpf(), pessoa.getCpf());
        pessoa.setNome(dados.getNome());
        pessoa.setCpf(dados.getCpf());
        pessoa.setDataNascimento(dados.getDataNascimento());
        pessoa.setNomeSocial(dados.getNomeSocial());
        return repository.save(pessoa);
    }

    @Transactional(readOnly = true)
    public PessoaFisica buscarPorId(Long id) {
        return repository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa física não encontrada: " + id));
    }

    @Transactional(readOnly = true)
    public PessoaFisicaResponse buscarResponsePorId(Long id) {
        return mapper.toResponse(buscarPorId(id));
    }

    @Transactional(readOnly = true)
    public List<PessoaFisica> listar() {
        return repository.findAllByAtivoTrue(Pageable.unpaged()).getContent();
    }

    @Transactional
    public void deletar(Long id) {
        PessoaFisica pessoa = buscarPorId(id);
        pessoa.setAtivo(false);
        repository.save(pessoa);
    }

    private void validarCpfDisponivel(String cpf, String cpfAtual) {
        if (!cpf.equals(cpfAtual) && repository.existsByCpf(cpf))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "CPF já cadastrado");
    }
}
