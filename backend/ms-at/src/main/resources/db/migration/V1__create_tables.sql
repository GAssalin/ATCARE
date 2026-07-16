-- Migration inicial do microserviço ms-at
-- Banco: MySQL 8+

CREATE TABLE at (
    id BIGINT NOT NULL AUTO_INCREMENT,
    pessoa_id BIGINT NOT NULL,
    criado_por BIGINT NULL,
    criado_em DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    atualizado_por BIGINT NULL,
    atualizado_em DATETIME(6) NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT pk_at PRIMARY KEY (id),
    CONSTRAINT uk_at_pessoa_id UNIQUE (pessoa_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE endereco (
    id BIGINT NOT NULL AUTO_INCREMENT,
    cep VARCHAR(9) NULL,
    logradouro VARCHAR(150) NULL,
    numero VARCHAR(20) NULL,
    complemento VARCHAR(100) NULL,
    bairro VARCHAR(100) NULL,
    cidade VARCHAR(100) NULL,
    estado CHAR(2) NULL,
    criado_por BIGINT NULL,
    criado_em DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    atualizado_por BIGINT NULL,
    atualizado_em DATETIME(6) NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT pk_endereco PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE especialidade (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    descricao VARCHAR(500) NULL,
    criado_por BIGINT NULL,
    criado_em DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    atualizado_por BIGINT NULL,
    atualizado_em DATETIME(6) NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT pk_especialidade PRIMARY KEY (id),
    CONSTRAINT uk_especialidade_nome UNIQUE (nome)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE formacao (
    id BIGINT NOT NULL AUTO_INCREMENT,
    at_id BIGINT NOT NULL,
    instituicao VARCHAR(200) NOT NULL,
    titulo VARCHAR(150) NOT NULL,
    ano_conclusao INT NULL,
    criado_por BIGINT NULL,
    criado_em DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    atualizado_por BIGINT NULL,
    atualizado_em DATETIME(6) NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT pk_formacao PRIMARY KEY (id),
    CONSTRAINT ck_formacao_ano_conclusao CHECK (
        ano_conclusao IS NULL OR ano_conclusao BETWEEN 1900 AND 2100
    ),
    CONSTRAINT fk_formacao_at FOREIGN KEY (at_id)
        REFERENCES at (id) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_formacao_at_id ON formacao (at_id);

CREATE TABLE curso (
    id BIGINT NOT NULL AUTO_INCREMENT,
    formacao_id BIGINT NOT NULL,
    nome VARCHAR(200) NOT NULL,
    instituicao VARCHAR(200) NOT NULL,
    carga_horaria INT NOT NULL,
    criado_por BIGINT NULL,
    criado_em DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    atualizado_por BIGINT NULL,
    atualizado_em DATETIME(6) NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT pk_curso PRIMARY KEY (id),
    CONSTRAINT ck_curso_carga_horaria CHECK (
        carga_horaria > 0
    ),
    CONSTRAINT fk_curso_formacao FOREIGN KEY (formacao_id)
        REFERENCES formacao (id) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_curso_formacao_id ON curso (formacao_id);

CREATE TABLE certificado (
    id BIGINT NOT NULL AUTO_INCREMENT,
    curso_id BIGINT NOT NULL,
    arquivo VARCHAR(500) NOT NULL,
    criado_por BIGINT NULL,
    criado_em DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    atualizado_por BIGINT NULL,
    atualizado_em DATETIME(6) NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT pk_certificado PRIMARY KEY (id),
    CONSTRAINT fk_certificado_curso FOREIGN KEY (curso_id)
        REFERENCES curso (id) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_certificado_curso_id ON certificado (curso_id);

CREATE TABLE validacao (
    id BIGINT NOT NULL AUTO_INCREMENT,
    certificado_id BIGINT NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDENTE',
    observacao VARCHAR(500) NULL,
    data_validacao DATETIME(6) NULL,
    usuario_validador BIGINT NULL,
    criado_por BIGINT NULL,
    criado_em DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    atualizado_por BIGINT NULL,
    atualizado_em DATETIME(6) NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT pk_validacao PRIMARY KEY (id),
    CONSTRAINT ck_validacao_status CHECK (
        status IN ('PENDENTE', 'APROVADO', 'REPROVADO')
    ),
    CONSTRAINT ck_validacao_dados CHECK (
        (status = 'PENDENTE' AND data_validacao IS NULL AND usuario_validador IS NULL)
        OR
        (status IN ('APROVADO', 'REPROVADO') AND data_validacao IS NOT NULL AND usuario_validador IS NOT NULL)
    ),
    CONSTRAINT fk_validacao_certificado FOREIGN KEY (certificado_id)
        REFERENCES certificado (id) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_validacao_certificado_id ON validacao (certificado_id);
CREATE INDEX idx_validacao_status ON validacao (status);

CREATE TABLE local_atendimento (
    id BIGINT NOT NULL AUTO_INCREMENT,
    at_id BIGINT NOT NULL,
    endereco_id BIGINT NULL,
    atendimento_domiciliar BOOLEAN NOT NULL DEFAULT FALSE,
    atendimento_online BOOLEAN NOT NULL DEFAULT FALSE,
    criado_por BIGINT NULL,
    criado_em DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    atualizado_por BIGINT NULL,
    atualizado_em DATETIME(6) NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT pk_local_atendimento PRIMARY KEY (id),
    CONSTRAINT ck_local_atendimento_modalidade CHECK (
        atendimento_domiciliar = TRUE OR atendimento_online = TRUE
    ),
    CONSTRAINT ck_local_atendimento_endereco CHECK (
        atendimento_domiciliar = FALSE OR endereco_id IS NOT NULL
    ),
    CONSTRAINT fk_local_atendimento_at FOREIGN KEY (at_id)
        REFERENCES at (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT fk_local_atendimento_endereco FOREIGN KEY (endereco_id)
        REFERENCES endereco (id) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_local_atendimento_at_id ON local_atendimento (at_id);
CREATE INDEX idx_local_atendimento_endereco_id ON local_atendimento (endereco_id);

CREATE TABLE at_especialidade (
    id BIGINT NOT NULL AUTO_INCREMENT,
    at_id BIGINT NOT NULL,
    especialidade_id BIGINT NOT NULL,
    criado_por BIGINT NULL,
    criado_em DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    atualizado_por BIGINT NULL,
    atualizado_em DATETIME(6) NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT pk_at_especialidade PRIMARY KEY (id),
    CONSTRAINT uk_at_especialidade UNIQUE (at_id, especialidade_id),
    CONSTRAINT fk_at_especialidade_at FOREIGN KEY (at_id)
        REFERENCES at (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT fk_at_especialidade_especialidade FOREIGN KEY (especialidade_id)
        REFERENCES especialidade (id) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_at_especialidade_especialidade_id
    ON at_especialidade (especialidade_id);

CREATE TABLE at_documento (
    id BIGINT NOT NULL AUTO_INCREMENT,
    at_id BIGINT NOT NULL,
    nome VARCHAR(150) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    arquivo_comprovante VARCHAR(500) NOT NULL,
    observacao VARCHAR(500) NULL,
    criado_por BIGINT NULL,
    criado_em DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    atualizado_por BIGINT NULL,
    atualizado_em DATETIME(6) NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT pk_at_documento PRIMARY KEY (id),
    CONSTRAINT ck_at_documento_tipo CHECK (
        tipo IN (
            'CPF', 'RG', 'COMPROVANTE_RESIDENCIA', 'CERTIFICADO',
            'COMPROVANTE_CONCLUSAO_CURSO', 'HISTORICO_ESCOLAR', 'CURRICULO',
            'REGISTRO_PROFISSIONAL', 'DIPLOMA', 'CNH', 'FOTO_PERFIL',
            'CARTEIRA_PROFISSIONAL'
        )
    ),
    CONSTRAINT fk_at_documento_at FOREIGN KEY (at_id)
        REFERENCES at (id) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_at_documento_at_id ON at_documento (at_id);
