package br.com.atcare.ms_autenticacao.service;

import br.com.atcare.core.auth.model.AuthenticatedUser;
import br.com.atcare.core.auth.service.TokenCoreService;
import br.com.atcare.ms_autenticacao.loader.AutenticacaoLoader;
import br.com.atcare.ms_autenticacao.model.dto.login.DadosLoginDto;
import br.com.atcare.ms_autenticacao.model.dto.token.DadosTokenDto;
import br.com.atcare.ms_autenticacao.model.dto.token.TokenUserDataDto;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTCreationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthTokenService {

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.tempo.exp.token}")
    private Integer tempoExpToken;

    private final AutenticacaoLoader autenticacaoLoader;
    private final TokenCoreService tokenCoreService;

    public DadosTokenDto autenticar(DadosLoginDto dados) {
        try {
            AuthenticatedUser user = autenticacaoLoader.authenticate(dados.email(), dados.senha());

            TokenUserDataDto tokenUser = new TokenUserDataDto(user.getUserId());

            return new DadosTokenDto(gerarAccessToken(tokenUser));
        } catch (DisabledException ex) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, ex.getMessage());
        } catch (BadCredentialsException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário ou senha inválidos");
        }
    }

    private String gerarAccessToken(TokenUserDataDto user) {
        validarTokenUserData(user);

        try {
            return JWT.create()
                    .withIssuer(issuer)
                    .withClaim("pId", user.pessoaId())
                    .withClaim("typ", "access")
                    .withExpiresAt(Instant.now().plusSeconds(tempoExpToken.longValue() * 60))
                    .sign(tokenCoreService.getAlgorithm());
        } catch (JWTCreationException ex) {
            throw new JWTCreationException("Erro ao gerar access token JWT", ex);
        }
    }

    private void validarTokenUserData(TokenUserDataDto user) {
        if (user == null)
            throw new IllegalArgumentException("Usuário inválido para geração do token");
    }

}