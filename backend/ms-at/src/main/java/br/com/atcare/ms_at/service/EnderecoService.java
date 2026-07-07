package br.com.atcare.ms_at.service;

import br.com.atcare.ms_at.model.entity.Endereco;
import br.com.atcare.ms_at.repository.EnderecoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository repository;

    public Endereco criar(Endereco entity) {
        return repository.save(entity);
    }

    public List<Endereco> listarTodos() {
        return repository.findAll();
    }

    public Endereco buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado. Id: " + id));
    }

    public Endereco atualizar(Long id, Endereco entity) {
        Endereco existente = buscarPorId(id);

        existente.setCep(entity.getCep());
        existente.setLogradouro(entity.getLogradouro());
        existente.setNumero(entity.getNumero());
        existente.setComplemento(entity.getComplemento());
        existente.setBairro(entity.getBairro());
        existente.setCidade(entity.getCidade());
        existente.setEstado(entity.getEstado());

        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.delete(buscarPorId(id));
    }

    public void ativar(Long id) {
        Endereco entity = buscarPorId(id);
        entity.setAtivo(true);
        repository.save(entity);
    }

    public void inativar(Long id) {
        Endereco entity = buscarPorId(id);
        entity.setAtivo(false);
        repository.save(entity);
    }
}