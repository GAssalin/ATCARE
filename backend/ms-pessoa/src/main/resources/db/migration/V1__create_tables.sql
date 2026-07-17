-- Migration inicial do ms-pessoa
-- Banco de dados: MySQL 8+

CREATE TABLE pessoa (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    tipo_pessoa VARCHAR(20) NOT NULL,
    criado_por BIGINT,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_por BIGINT,
    atualizado_em TIMESTAMP NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT ck_pessoa_tipo
        CHECK (tipo_pessoa IN ('FISICA', 'JURIDICA'))
);

CREATE TABLE pessoa_fisica (
    id BIGINT PRIMARY KEY,
    cpf VARCHAR(11) NOT NULL,
    data_nascimento DATE NOT NULL,
    nome_social VARCHAR(150),

    CONSTRAINT uk_pessoa_fisica_cpf UNIQUE (cpf),
    CONSTRAINT fk_pessoa_fisica_pessoa
        FOREIGN KEY (id) REFERENCES pessoa (id)
);

CREATE TABLE pessoa_juridica (
    id BIGINT PRIMARY KEY,
    cnpj VARCHAR(14) NOT NULL,
    razao_social VARCHAR(200) NOT NULL,
    nome_fantasia VARCHAR(200),

    CONSTRAINT uk_pessoa_juridica_cnpj UNIQUE (cnpj),
    CONSTRAINT fk_pessoa_juridica_pessoa
        FOREIGN KEY (id) REFERENCES pessoa (id)
);

CREATE TABLE municipio (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    uf VARCHAR(2) NOT NULL,
    codigo_ibge VARCHAR(10),
    criado_por BIGINT,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_por BIGINT,
    atualizado_em TIMESTAMP NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT ck_municipio_uf CHECK (uf IN (
        'AC', 'AL', 'AP', 'AM', 'BA', 'CE', 'DF', 'ES', 'GO',
        'MA', 'MT', 'MS', 'MG', 'PA', 'PB', 'PR', 'PE', 'PI',
        'RJ', 'RN', 'RS', 'RO', 'RR', 'SC', 'SP', 'SE', 'TO'
    ))
);

CREATE TABLE endereco (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pessoa_id BIGINT NOT NULL,
    tipo_endereco VARCHAR(20) NOT NULL,
    logradouro VARCHAR(150) NOT NULL,
    numero VARCHAR(20) NOT NULL,
    complemento VARCHAR(100),
    bairro VARCHAR(100) NOT NULL,
    municipio_id BIGINT NOT NULL,
    cep VARCHAR(8) NOT NULL,
    principal BOOLEAN NOT NULL DEFAULT FALSE,
    criado_por BIGINT,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_por BIGINT,
    atualizado_em TIMESTAMP NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT ck_endereco_tipo
        CHECK (tipo_endereco IN ('RESIDENCIAL', 'COMERCIAL', 'ENTREGA')),
    CONSTRAINT fk_endereco_pessoa
        FOREIGN KEY (pessoa_id) REFERENCES pessoa (id),
    CONSTRAINT fk_endereco_municipio
        FOREIGN KEY (municipio_id) REFERENCES municipio (id)
);

CREATE TABLE contato (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pessoa_id BIGINT NOT NULL,
    tipo_contato VARCHAR(20) NOT NULL,
    valor VARCHAR(150) NOT NULL,
    principal BOOLEAN NOT NULL DEFAULT FALSE,
    criado_por BIGINT,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_por BIGINT,
    atualizado_em TIMESTAMP NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT ck_contato_tipo
        CHECK (tipo_contato IN ('TELEFONE', 'EMAIL', 'CELULAR')),
    CONSTRAINT fk_contato_pessoa
        FOREIGN KEY (pessoa_id) REFERENCES pessoa (id)
);

CREATE TABLE documento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pessoa_id BIGINT NOT NULL,
    tipo_documento VARCHAR(20) NOT NULL,
    numero VARCHAR(50) NOT NULL,
    orgao_emissor VARCHAR(50),
    data_emissao DATE,
    criado_por BIGINT,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_por BIGINT,
    atualizado_em TIMESTAMP NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT ck_documento_tipo
        CHECK (tipo_documento IN ('CPF', 'CNPJ', 'RG', 'PASSAPORTE')),
    CONSTRAINT fk_documento_pessoa
        FOREIGN KEY (pessoa_id) REFERENCES pessoa (id)
);

CREATE TABLE tipo_relacao_pessoa (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(255),
    criado_por BIGINT,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_por BIGINT,
    atualizado_em TIMESTAMP NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT uk_tipo_relacao_pessoa_nome UNIQUE (nome)
);

CREATE TABLE pessoa_relacao (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pessoa_id BIGINT NOT NULL,
    relacionado_id BIGINT NOT NULL,
    tipo_relacao_pessoa_id BIGINT NOT NULL,
    criado_por BIGINT,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_por BIGINT,
    atualizado_em TIMESTAMP NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT ck_pessoa_relacao_pessoas_distintas
        CHECK (pessoa_id <> relacionado_id),
    CONSTRAINT uk_pessoa_relacao
        UNIQUE (pessoa_id, relacionado_id, tipo_relacao_pessoa_id),
    CONSTRAINT fk_pessoa_relacao_pessoa
        FOREIGN KEY (pessoa_id) REFERENCES pessoa (id),
    CONSTRAINT fk_pessoa_relacao_relacionado
        FOREIGN KEY (relacionado_id) REFERENCES pessoa (id),
    CONSTRAINT fk_pessoa_relacao_tipo
        FOREIGN KEY (tipo_relacao_pessoa_id) REFERENCES tipo_relacao_pessoa (id)
);

CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pessoa_id BIGINT,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(150) NOT NULL,
    email_verificado BOOLEAN NOT NULL DEFAULT FALSE,
    criado_por BIGINT,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_por BIGINT,
    atualizado_em TIMESTAMP NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT uk_usuario_email UNIQUE (email),
    CONSTRAINT fk_usuario_pessoa
        FOREIGN KEY (pessoa_id) REFERENCES pessoa (id)
);

CREATE TABLE usuario_email_verificacao (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    token_hash VARCHAR(64) NOT NULL,
    expiracao TIMESTAMP NOT NULL,
    utilizado BOOLEAN NOT NULL DEFAULT FALSE,
    criado_por BIGINT,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_por BIGINT,
    atualizado_em TIMESTAMP NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT uk_usuario_email_verificacao_token UNIQUE (token_hash),
    CONSTRAINT fk_usuario_email_verificacao_usuario
        FOREIGN KEY (usuario_id) REFERENCES usuario (id)
);

CREATE INDEX idx_endereco_pessoa_id ON endereco (pessoa_id);
CREATE INDEX idx_endereco_municipio_id ON endereco (municipio_id);
CREATE INDEX idx_contato_pessoa_id ON contato (pessoa_id);
CREATE INDEX idx_documento_pessoa_id ON documento (pessoa_id);
CREATE INDEX idx_documento_tipo_numero ON documento (tipo_documento, numero);
CREATE INDEX idx_pessoa_relacao_pessoa_id ON pessoa_relacao (pessoa_id);
CREATE INDEX idx_pessoa_relacao_relacionado_id ON pessoa_relacao (relacionado_id);
CREATE INDEX idx_pessoa_relacao_tipo_id ON pessoa_relacao (tipo_relacao_pessoa_id);
CREATE INDEX idx_usuario_pessoa_id ON usuario (pessoa_id);
CREATE INDEX idx_usuario_email_verificacao_usuario_id
    ON usuario_email_verificacao (usuario_id);
CREATE INDEX idx_usuario_email_verificacao_expiracao
    ON usuario_email_verificacao (expiracao);
