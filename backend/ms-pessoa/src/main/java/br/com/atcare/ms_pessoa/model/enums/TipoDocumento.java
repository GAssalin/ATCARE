package br.com.atcare.ms_pessoa.model.enums;

import lombok.Getter;

/**
 * Enum que representa os tipos de documentos pessoais cadastrados no ATCARE.
 * <p>
 * Utilizado para registro, validação e organização de informações
 * documentais pertencentes a pessoas físicas e jurídicas.
 */
@Getter
public enum TipoDocumento {

    /**
     * Cadastro de Pessoa Física.
     */
    CPF("CPF"),

    /**
     * Cadastro Nacional da Pessoa Jurídica.
     */
    CNPJ("CNPJ"),

    /**
     * Registro Geral de identidade (RG).
     */
    RG("RG"),

    /**
     * Passaporte válido emitido para cidadãos.
     */
    PASSAPORTE("Passaporte");

    private final String descricao;

    TipoDocumento(String descricao) {
        this.descricao = descricao;
    }
}
