package br.com.atcare.ms_pessoa.controller;

import br.com.atcare.ms_pessoa.model.dto.pessoa.PessoaJuridicaListDTO;
import br.com.atcare.ms_pessoa.model.dto.pessoa.PessoaJuridicaRequest;
import br.com.atcare.ms_pessoa.model.dto.pessoa.PessoaJuridicaResponse;
import br.com.atcare.ms_pessoa.service.PessoaJuridicaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/pessoas-juridicas")
@RequiredArgsConstructor
@Validated
@Tag(
        name = "Pessoas Jurídicas",
        description = "Endpoints para criação, atualização, consulta, listagem e remoção de pessoas jurídicas."
)
public class PessoaJuridicaController {

    private final PessoaJuridicaService pessoaJuridicaService;

    @Operation(
            summary = "Cadastrar pessoa jurídica",
            description = "Cria uma nova pessoa jurídica no sistema.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Pessoa jurídica criada com sucesso",
                            content = @Content(
                                    schema = @Schema(implementation = PessoaJuridicaResponse.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                    @ApiResponse(responseCode = "403", description = "Sem permissão"),
                    @ApiResponse(responseCode = "503", description = "Serviço indisponível")
            }
    )
    @PostMapping
    public ResponseEntity<PessoaJuridicaResponse> criar(@Valid @RequestBody PessoaJuridicaRequest request) {
        return ResponseEntity.ok(new PessoaJuridicaResponse(0L, "teste pj", null, null, null));
    }

    @Operation(
            summary = "Atualizar pessoa jurídica",
            description = "Atualiza os dados de uma pessoa jurídica existente.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Pessoa jurídica atualizada com sucesso",
                            content = @Content(
                                    schema = @Schema(implementation = PessoaJuridicaResponse.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Pessoa jurídica não encontrada"),
                    @ApiResponse(responseCode = "403", description = "Sem permissão"),
                    @ApiResponse(responseCode = "503", description = "Serviço indisponível")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<PessoaJuridicaResponse> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody PessoaJuridicaRequest request
    ) {
        return ResponseEntity.ok(new PessoaJuridicaResponse(0L, "teste pj", null, null, null));
    }

    @Operation(
            summary = "Buscar pessoa jurídica por ID",
            description = "Retorna os dados de uma pessoa jurídica pelo identificador.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Consulta realizada com sucesso",
                            content = @Content(
                                    schema = @Schema(implementation = PessoaJuridicaResponse.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Pessoa jurídica não encontrada"),
                    @ApiResponse(responseCode = "403", description = "Sem permissão"),
                    @ApiResponse(responseCode = "503", description = "Serviço indisponível")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<PessoaJuridicaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(new PessoaJuridicaResponse(0L, "teste pj", null, null, null));
    }

    @Operation(
            summary = "Buscar pessoa jurídica por CNPJ",
            description = "Retorna os dados de uma pessoa jurídica pelo CNPJ.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Consulta realizada com sucesso",
                            content = @Content(
                                    schema = @Schema(implementation = PessoaJuridicaResponse.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Pessoa jurídica não encontrada"),
                    @ApiResponse(responseCode = "403", description = "Sem permissão"),
                    @ApiResponse(responseCode = "503", description = "Serviço indisponível")
            }
    )
    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<PessoaJuridicaResponse> buscarPorCnpj(@PathVariable String cnpj) {
        return ResponseEntity.ok(new PessoaJuridicaResponse(0L, "teste pj", null, null, null));
    }

    @Operation(
            summary = "Listar todas as pessoas jurídicas",
            description = "Retorna todas as pessoas jurídicas cadastradas.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Consulta realizada com sucesso",
                            content = @Content(
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = PessoaJuridicaListDTO.class)
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "403", description = "Sem permissão"),
                    @ApiResponse(responseCode = "503", description = "Serviço indisponível")
            }
    )
    @GetMapping
    public ResponseEntity<List<PessoaJuridicaListDTO>> listarTodas() {
        return ResponseEntity.ok(List.of());
    }

    @Operation(
            summary = "Listar pessoas jurídicas por nome fantasia",
            description = "Retorna pessoas jurídicas filtrando pelo nome fantasia.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Consulta realizada com sucesso",
                            content = @Content(
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = PessoaJuridicaListDTO.class)
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "403", description = "Sem permissão"),
                    @ApiResponse(responseCode = "503", description = "Serviço indisponível")
            }
    )
    @GetMapping("/nome-fantasia/{nomeFantasia}")
    public ResponseEntity<List<PessoaJuridicaListDTO>> listarPorNomeFantasia(@PathVariable String nomeFantasia) {
        return ResponseEntity.ok(List.of());
    }

    @Operation(
            summary = "Listar pessoas jurídicas por nome",
            description = "Retorna pessoas jurídicas cujo nome contenha o valor informado (ignore case).",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Consulta realizada com sucesso",
                            content = @Content(
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = PessoaJuridicaListDTO.class)
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "403", description = "Sem permissão"),
                    @ApiResponse(responseCode = "503", description = "Serviço indisponível")
            }
    )
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<PessoaJuridicaListDTO>> listarPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(List.of());
    }

    @Operation(
            summary = "Remover pessoa jurídica",
            description = "Remove uma pessoa jurídica pelo identificador.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pessoa jurídica removida com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Pessoa jurídica não encontrada"),
                    @ApiResponse(responseCode = "403", description = "Sem permissão"),
                    @ApiResponse(responseCode = "503", description = "Serviço indisponível")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }
}
