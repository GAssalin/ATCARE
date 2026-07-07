package br.com.atcare.gateway.config;

import br.com.atcare.gateway.filter.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class GatewayConfig {

    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {
        return builder.routes()

                .route("docs-page", r -> r
                        .path("/docs")
                        .uri("no://op") // rota interna, sem backend
                )

                // ===============================
                // ROTA: MS-AUTENTICACAO (sem JWT)
                // ===============================
                .route("ms-auth", r -> r
                        .path("/ms-auth/**")
                        .filters(f -> f
                                .stripPrefix(1)
                        )
                        .uri("lb://ms-auth")
                )

                // ===============================
                // ROTA: MS-PESSOAS (COM JWT)
                // ===============================
                .route("ms-pessoa", r -> r
                        .path("/ms-pessoa/**")
                        .filters(f -> f
                                .stripPrefix(1)
                                .filter(jwtAuthFilter)
                        )
                        .uri("lb://ms-pessoa")
                )

                // ===============================
                // ROTA: MS-PESSOAS (COM JWT)
                // ===============================
                .route("ms-at", r -> r
                        .path("/ms-at/**")
                        .filters(f -> f
                                .stripPrefix(1)
                                .filter(jwtAuthFilter)
                        )
                        .uri("lb://ms-at")
                )

                .build();
    }
}