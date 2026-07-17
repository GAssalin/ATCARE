package br.com.atcare.ms_pessoa.service;

import br.com.atcare.ms_pessoa.mapper.UsuarioEmailVerificacaoMapper;
import br.com.atcare.ms_pessoa.model.dto.response.UsuarioEmailVerificacaoResponse;
import br.com.atcare.ms_pessoa.model.entity.Usuario;
import br.com.atcare.ms_pessoa.model.entity.UsuarioEmailVerificacao;
import br.com.atcare.ms_pessoa.repository.UsuarioEmailVerificacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.HexFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioEmailVerificacaoService {
    private final UsuarioEmailVerificacaoRepository repository;
    private final UsuarioService usuarioService;
    private final UsuarioEmailVerificacaoMapper mapper;

    @Transactional
    public UsuarioEmailVerificacaoResponse criar(Long usuarioId, String token, LocalDateTime expiracao) {
        Usuario u = usuarioService.buscarPorId(usuarioId);
        repository.findFirstByUsuarioIdAndUtilizadoFalseAndAtivoTrueOrderByExpiracaoDesc(usuarioId).ifPresent(e -> {
            e.setAtivo(false);
            repository.save(e);
        });
        return mapper.toResponse(repository.save(mapper.toEntity(u, hash(token), expiracao)));
    }

    @Transactional
    public UsuarioEmailVerificacaoResponse confirmar(String token) {
        UsuarioEmailVerificacao e = repository.findByTokenHashAndUtilizadoFalseAndAtivoTrue(hash(token)).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token inválido ou já utilizado"));
        if (e.getExpiracao().isBefore(LocalDateTime.now()))
            throw new ResponseStatusException(HttpStatus.GONE, "Token expirado");
        e.setUtilizado(true);
        usuarioService.marcarEmailVerificado(e.getUsuario());
        return mapper.toResponse(repository.save(e));
    }

    @Transactional(readOnly = true)
    public UsuarioEmailVerificacaoResponse buscarResponsePorId(Long id) {
        return mapper.toResponse(buscarPorId(id));
    }

    @Transactional
    public UsuarioEmailVerificacao salvar(UsuarioEmailVerificacao e) {
        e.setId(null);
        return repository.save(e);
    }

    @Transactional
    public UsuarioEmailVerificacao atualizar(Long id, UsuarioEmailVerificacao dados) {
        UsuarioEmailVerificacao e = buscarPorId(id);
        e.setUsuario(dados.getUsuario());
        e.setTokenHash(dados.getTokenHash());
        e.setExpiracao(dados.getExpiracao());
        e.setUtilizado(dados.isUtilizado());
        return repository.save(e);
    }

    @Transactional(readOnly = true)
    public UsuarioEmailVerificacao buscarPorId(Long id) {
        return repository.findById(id).filter(UsuarioEmailVerificacao::getAtivo).orElseThrow(() -> new EntityNotFoundException("Verificação de e-mail não encontrada: " + id));
    }

    @Transactional(readOnly = true)
    public List<UsuarioEmailVerificacao> listar() {
        return repository.findAll().stream().filter(UsuarioEmailVerificacao::getAtivo).toList();
    }

    @Transactional
    public void deletar(Long id) {
        UsuarioEmailVerificacao e = buscarPorId(id);
        e.setAtivo(false);
        repository.save(e);
    }

    private String hash(String token) {
        try {
            return HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256").digest(token.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 indisponível", e);
        }
    }
}
