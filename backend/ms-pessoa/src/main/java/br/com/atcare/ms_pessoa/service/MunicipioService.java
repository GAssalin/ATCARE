package br.com.atcare.ms_pessoa.service;

import br.com.atcare.ms_pessoa.mapper.MunicipioMapper;
import br.com.atcare.ms_pessoa.model.dto.request.MunicipioRequest;
import br.com.atcare.ms_pessoa.model.dto.response.MunicipioResponse;
import br.com.atcare.ms_pessoa.model.entity.Municipio;
import br.com.atcare.ms_pessoa.model.enums.Uf;
import br.com.atcare.ms_pessoa.repository.MunicipioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MunicipioService {
    private final MunicipioRepository repository;
    private final MunicipioMapper mapper;

    @Transactional
    public MunicipioResponse salvar(MunicipioRequest r) {
        validarCodigo(r.codigoIbge(), null);
        return mapper.toResponse(repository.save(mapper.toEntity(r)));
    }

    @Transactional
    public MunicipioResponse atualizar(Long id, MunicipioRequest r) {
        Municipio e = buscarEntidade(id);
        validarCodigo(r.codigoIbge(), e);
        mapper.updateEntity(e, r);
        return mapper.toResponse(repository.save(e));
    }

    @Transactional(readOnly = true)
    public MunicipioResponse buscarPorId(Long id) {
        return mapper.toResponse(buscarEntidade(id));
    }

    @Transactional(readOnly = true)
    public List<MunicipioResponse> listarPorUf(Uf uf) {
        return repository.findAllByUfAndAtivoTrueOrderByNomeAsc(uf).stream().map(mapper::toResponse).toList();
    }

    @Transactional
    public void deletar(Long id) {
        Municipio e = buscarEntidade(id);
        e.setAtivo(false);
        repository.save(e);
    }

    Municipio buscarEntidade(Long id) {
        return repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new EntityNotFoundException("Município não encontrado: " + id));
    }

    private void validarCodigo(String codigo, Municipio atual) {
        if (codigo != null)
            repository.findByCodigoIbgeAndAtivoTrue(codigo).filter(e -> atual == null || !e.getId().equals(atual.getId())).ifPresent(e -> {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Código IBGE já cadastrado");
            });
    }
}
