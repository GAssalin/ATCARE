package br.com.atcare.core.auth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenCoreService {
    private final String segredo;
    private final String issuer;

    public TokenCoreService(
            @Value("${jwt.secret}") String segredo,
            @Value("${jwt.issuer}") String issuer
    ) {
        this.segredo = segredo;
        this.issuer = issuer;
    }

    public DecodedJWT validarToken(String token) {
        return JWT.require(getAlgorithm())
                .withIssuer(issuer)
                .withClaim("typ", "access")
                .build()
                .verify(token);
    }

    public Algorithm getAlgorithm() {
        return Algorithm.HMAC256(segredo);
    }
}
