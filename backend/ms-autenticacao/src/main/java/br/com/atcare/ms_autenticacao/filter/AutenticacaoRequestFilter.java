package br.com.atcare.ms_autenticacao.filter;

import br.com.atcare.core.auth.model.AuthenticatedUser;
import br.com.atcare.core.auth.service.TokenCoreService;
import br.com.atcare.core.usuario.auth.context.UserContext;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AutenticacaoRequestFilter extends OncePerRequestFilter {

    private TokenCoreService tokenCoreService;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath().replace("ms-auth/", "");

        return path.startsWith("/actuator")
                || path.startsWith("/swagger")
                || path.startsWith("/v3/api-docs")
                || path.startsWith("/error");
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String token = recuperarTokenRequisicao(request);

        if (token != null) {

            DecodedJWT decodedJWT;
            try {
                decodedJWT = tokenCoreService.validarToken(token);
            } catch (Exception ex) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token inválido ou expirado");
            }

            Long pessoaId = decodedJWT.getClaim("pId").asLong();
            String username = decodedJWT.getSubject();

            AuthenticatedUser principal = new AuthenticatedUser(
                    pessoaId,
                    username,
                    null
            );

            UserContext.setUsuarioId(pessoaId);

            Authentication authentication = new UsernamePasswordAuthenticationToken(principal, null, List.of());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        try {
            filterChain.doFilter(request, response);
        } finally {
            UserContext.clear();
        }
    }

    private String recuperarTokenRequisicao(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer "))
            return authorizationHeader.substring(7);
        return null;
    }
}
