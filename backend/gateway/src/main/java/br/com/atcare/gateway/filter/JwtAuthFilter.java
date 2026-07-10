package br.com.atcare.gateway.filter;

import br.com.atcare.core.auth.service.TokenCoreService;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

import static br.com.atcare.core.base.error.ErrorUtils.respostaErro;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter implements GatewayFilter {

    private final TokenCoreService tokenCoreService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        try {
            log.info(">>> PASSOU PELO GATEWAY: {}", exchange.getRequest().getURI());

            String path = exchange.getRequest().getURI().getPath();

            // ==============================
            // ROTAS SEM JWT
            // ==============================
            if (path.contains("/v1/auth/login")) { return chain.filter(exchange); }

            DecodedJWT jwt = tokenCoreService.validarToken(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION));
            Long pessoaId = jwt.getClaim("pId").asLong();

            if (pessoaId == null)
                throw new JWTVerificationException("pId não identificado.");

            ServerHttpRequest mutatedRequest = exchange.getRequest()
                    .mutate()
                    .header("X-P-Id", pessoaId.toString())
                    .build();

            return chain.filter(exchange.mutate().request(mutatedRequest).build());
        } catch (JWTVerificationException e) {
            log.error(e.getMessage());
            return respostaErro(exchange, HttpStatus.UNAUTHORIZED, "Unauthorized", "Token ausente, inválido ou expirado.");
        }
    }
}
