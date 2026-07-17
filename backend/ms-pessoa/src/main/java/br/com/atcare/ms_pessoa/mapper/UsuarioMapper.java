package br.com.atcare.ms_pessoa.mapper;

import br.com.atcare.ms_pessoa.model.dto.request.UsuarioRequest;
import br.com.atcare.ms_pessoa.model.dto.response.UsuarioResponse;
import br.com.atcare.ms_pessoa.model.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    public Usuario toEntity(UsuarioRequest request, String passwordCodificado) {
        Usuario entity = new Usuario();
        updateEntity(entity, request, passwordCodificado);
        return entity;
    }

    public void updateEntity(Usuario entity, UsuarioRequest request, String passwordCodificado) {
        entity.setPessoaId(request.pessoaId());
        entity.setEmail(request.email());
        if (passwordCodificado != null && !passwordCodificado.isBlank()) entity.setPassword(passwordCodificado);
    }

    public UsuarioResponse toResponse(Usuario entity) {
        if (entity == null) return null;
        return new UsuarioResponse(entity.getId(), entity.getPessoaId(), entity.getEmail(),
                entity.isEmailVerificado(), MapperSupport.auditoria(entity));
    }
}
