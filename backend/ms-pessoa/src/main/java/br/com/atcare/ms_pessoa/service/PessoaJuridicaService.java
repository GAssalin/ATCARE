package br.com.atcare.ms_pessoa.service;

import br.com.atcare.ms_pessoa.mapper.PessoaJuridicaMapper;
import br.com.atcare.ms_pessoa.model.dto.request.PessoaJuridicaRequest;
import br.com.atcare.ms_pessoa.model.dto.response.PessoaJuridicaResponse;
import br.com.atcare.ms_pessoa.model.entity.PessoaJuridica;
import br.com.atcare.ms_pessoa.repository.PessoaJuridicaRepository;
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
public class PessoaJuridicaService {
    private final PessoaJuridicaRepository repository;
    private final PessoaJuridicaMapper mapper;

    @Transactional
    public PessoaJuridicaResponse salvar(PessoaJuridicaRequest request) {
        validarCnpjDisponivel(request.cnpj(), null);
        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    @Transactional
    public PessoaJuridicaResponse atualizar(Long id, PessoaJuridicaRequest request) {
        PessoaJuridica pessoa = buscarPorId(id);
        validarCnpjDisponivel(request.cnpj(), pessoa.getCnpj());
        mapper.updateEntity(pessoa, request);
        return mapper.toResponse(repository.save(pessoa));
    }

    @Transactional(readOnly = true)
    public Page<PessoaJuridicaResponse> listar(String razaoSocial, Pageable pageable) {
        Page<PessoaJuridica> pagina = razaoSocial == null || razaoSocial.isBlank()
                ? repository.findAllByAtivoTrue(pageable)
                : repository.findByRazaoSocialContainingIgnoreCaseAndAtivoTrue(razaoSocial, pageable);
        return pagina.map(mapper::toResponse);
    }

    @Transactional
    public PessoaJuridica salvar(PessoaJuridica pessoa) {
        validarCnpjDisponivel(pessoa.getCnpj(), null);
        pessoa.setId(null);
        return repository.save(pessoa);
    }

    @Transactional
    public PessoaJuridica atualizar(Long id, PessoaJuridica dados) {
        PessoaJuridica pessoa = buscarPorId(id);
        validarCnpjDisponivel(dados.getCnpj(), pessoa.getCnpj());
        pessoa.setNome(dados.getNome());
        pessoa.setCnpj(dados.getCnpj());
        pessoa.setRazaoSocial(dados.getRazaoSocial());
        pessoa.setNomeFantasia(dados.getNomeFantasia());
        return repository.save(pessoa);
    }

    @Transactional(readOnly = true)
    public PessoaJuridica buscarPorId(Long id) {
        return repository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa jurídica não encontrada: " + id));
    }

    @Transactional(readOnly = true)
    public PessoaJuridicaResponse buscarResponsePorId(Long id) {
        return mapper.toResponse(buscarPorId(id));
    }

    @Transactional(readOnly = true)
    public List<PessoaJuridica> listar() {
        return repository.findAllByAtivoTrue(Pageable.unpaged()).getContent();
    }

    @Transactional
    public void deletar(Long id) {
        PessoaJuridica pessoa = buscarPorId(id);
        pessoa.setAtivo(false);
        repository.save(pessoa);
    }

    private void validarCnpjDisponivel(String cnpj, String cnpjAtual) {
        if (!cnpj.equals(cnpjAtual) && repository.existsByCnpj(cnpj))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "CNPJ já cadastrado");
    }
}
