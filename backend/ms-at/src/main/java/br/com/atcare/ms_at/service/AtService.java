package br.com.atcare.ms_at.service;

import br.com.atcare.ms_at.model.entity.At;
import br.com.atcare.ms_at.repository.AtRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AtService {

    private final AtRepository atRepository;

    public At criar(At at) {
        return atRepository.save(at);
    }

    public List<At> listarTodos() {
        return atRepository.findAll();
    }

    public At buscarPorId(Long id) {
        return atRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AT não encontrado com id: " + id));
    }

    public At atualizar(Long id, At atAtualizado) {
        At atExistente = buscarPorId(id);

        atExistente.setPessoaId(atAtualizado.getPessoaId());
        atExistente.setFormacoes(atAtualizado.getFormacoes());
        atExistente.setLocaisAtendimento(atAtualizado.getLocaisAtendimento());
        atExistente.setEspecialidades(atAtualizado.getEspecialidades());
        atExistente.setDocumentos(atAtualizado.getDocumentos());

        return atRepository.save(atExistente);
    }

    public void deletar(Long id) {
        At at = buscarPorId(id);
        atRepository.delete(at);
    }

    public void inativar(Long id) {
        At at = buscarPorId(id);
        at.setAtivo(false);
        atRepository.save(at);
    }

    public void ativar(Long id) {
        At at = buscarPorId(id);
        at.setAtivo(true);
        atRepository.save(at);
    }
}