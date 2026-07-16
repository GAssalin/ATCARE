package br.com.atcare.ms_at.service;

import br.com.atcare.ms_at.mapper.EnderecoMapper;
import br.com.atcare.ms_at.model.dto.request.EnderecoRequestDTO;
import br.com.atcare.ms_at.model.dto.response.EnderecoResponseDTO;
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
    private final EnderecoMapper mapper;

    public EnderecoResponseDTO criar(EnderecoRequestDTO dto) {
        return mapper.toResponse(repository.save(mapper.toEntity(dto)));
    }

    public List<EnderecoResponseDTO> listarTodos() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    public EnderecoResponseDTO buscarPorId(Long id) {
        return mapper.toResponse(buscarEntidadePorId(id));
    }

    public EnderecoResponseDTO atualizar(Long id, EnderecoRequestDTO dto) {
        Endereco existente = buscarEntidadePorId(id);
        Endereco dados = mapper.toEntity(dto);
        existente.setCep(dados.getCep());
        existente.setLogradouro(dados.getLogradouro());
        existente.setNumero(dados.getNumero());
        existente.setComplemento(dados.getComplemento());
        existente.setBairro(dados.getBairro());
        existente.setCidade(dados.getCidade());
        existente.setEstado(dados.getEstado());
        return mapper.toResponse(repository.save(existente));
    }

    public void deletar(Long id) {
        repository.delete(buscarEntidadePorId(id));
    }

    public void ativar(Long id) {
        alterarAtivo(id, true);
    }

    public void inativar(Long id) {
        alterarAtivo(id, false);
    }

    public Endereco buscarEntidadePorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado. Id: " + id));
    }

    private void alterarAtivo(Long id, boolean ativo) {
        Endereco entity = buscarEntidadePorId(id);
        entity.setAtivo(ativo);
        repository.save(entity);
    }
}
