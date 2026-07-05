package br.com.atcare.ms_pessoa.model.enums;

import lombok.Getter;

/**
 * Enum que representa os tipos de contato associados a uma pessoa no ATCARE.
 * <p>
 * Auxilia na categorização de canais de comunicação, permitindo
 * diferenciação entre contatos pessoais, profissionais e outros meios.
 */
@Getter
public enum TipoContato {

    /**
     * Contato telefônico principal da pessoa.
     */
    TELEFONE("Telefone"),

    /**
     * Endereço de e-mail utilizado para comunicação.
     */
    EMAIL("E-mail"),

    /**
     * Telefone celular para contato direto ou envio de mensagens.
     */
    CELULAR("Celular");

    private final String descricao;

    TipoContato(String descricao) {
        this.descricao = descricao;
    }
}
