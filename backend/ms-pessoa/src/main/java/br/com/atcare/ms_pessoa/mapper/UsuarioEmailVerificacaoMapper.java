package br.com.atcare.ms_pessoa.mapper;

import br.com.atcare.ms_pessoa.model.dto.response.UsuarioEmailVerificacaoResponse;
import br.com.atcare.ms_pessoa.model.entity.Usuario;
import br.com.atcare.ms_pessoa.model.entity.UsuarioEmailVerificacao;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class UsuarioEmailVerificacaoMapper {
    public UsuarioEmailVerificacao toEntity(Usuario usuario, String tokenHash, LocalDateTime expiracao) {
        UsuarioEmailVerificacao entity = new UsuarioEmailVerificacao();
        entity.setUsuario(usuario);
        entity.setTokenHash(tokenHash);
        entity.setExpiracao(expiracao);
        entity.setUtilizado(false);
        return entity;
    }

    public UsuarioEmailVerificacaoResponse toResponse(UsuarioEmailVerificacao entity) {
        if (entity == null) return null;
        Long usuarioId = entity.getUsuario() == null ? null : entity.getUsuario().getId();
        return new UsuarioEmailVerificacaoResponse(entity.getId(), usuarioId, entity.getExpiracao(),
                entity.isUtilizado(), MapperSupport.auditoria(entity));
    }
}
