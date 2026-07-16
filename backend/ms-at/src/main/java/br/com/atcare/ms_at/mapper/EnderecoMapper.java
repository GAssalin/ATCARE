package br.com.atcare.ms_at.mapper;

import br.com.atcare.ms_at.model.dto.request.EnderecoRequestDTO;
import br.com.atcare.ms_at.model.dto.response.EnderecoResponseDTO;
import br.com.atcare.ms_at.model.entity.Endereco;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapper {
    public Endereco toEntity(EnderecoRequestDTO dto) {
        if (dto == null) return null;
        Endereco entity = new Endereco();
        entity.setId(dto.id());
        entity.setCep(dto.cep());
        entity.setLogradouro(dto.logradouro());
        entity.setNumero(dto.numero());
        entity.setComplemento(dto.complemento());
        entity.setBairro(dto.bairro());
        entity.setCidade(dto.cidade());
        entity.setEstado(dto.estado());
        return entity;
    }

    public EnderecoResponseDTO toResponse(Endereco entity) {
        if (entity == null) return null;
        return new EnderecoResponseDTO(entity.getId(), entity.getCep(), entity.getLogradouro(),
                entity.getNumero(), entity.getComplemento(), entity.getBairro(),
                entity.getCidade(), entity.getEstado());
    }
}
