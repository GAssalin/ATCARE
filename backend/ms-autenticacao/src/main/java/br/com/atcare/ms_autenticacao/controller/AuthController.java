package br.com.atcare.ms_autenticacao.controller;

import br.com.atcare.ms_autenticacao.model.dto.login.DadosLoginDto;
import br.com.atcare.ms_autenticacao.model.dto.token.DadosTokenDto;
import br.com.atcare.ms_autenticacao.service.AuthTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
@Tag(
        name = "Autenticação",
        description = "Endpoints responsáveis por login, renovação de tokens e gerenciamento de credenciais de acesso."
)
public class AuthController {

    private final AuthTokenService authTokenService;

    @Operation(
            summary = "Efetuar login",
            description = "Autentica o usuário com email e senha, gerando um token JWT de acesso e um refresh token.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Login realizado com sucesso",
                            content = @Content(schema = @Schema(implementation = DadosTokenDto.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                    @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
                    @ApiResponse(responseCode = "403", description = "Usuário desabilitado"),
                    @ApiResponse(responseCode = "503", description = "Serviço temporariamente indisponível")
            }
    )
    @PostMapping("/login")
    public ResponseEntity<DadosTokenDto> efetuarLogin(@Valid @RequestBody DadosLoginDto dados) {
        return ResponseEntity.ok(authTokenService.autenticar(dados));
    }

}
