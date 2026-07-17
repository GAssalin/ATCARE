-- Carga inicial fictícia da tabela at
-- Dependência: V1__create_tables.sql

INSERT INTO at (
    id,
    pessoa_id,
    criado_por,
    criado_em,
    atualizado_por,
    atualizado_em,
    ativo
) VALUES
    (1, 1001, 1, CURRENT_TIMESTAMP(6), NULL, NULL, TRUE),
    (2, 1002, 1, CURRENT_TIMESTAMP(6), NULL, NULL, TRUE),
    (3, 1003, 1, CURRENT_TIMESTAMP(6), NULL, NULL, TRUE),
    (4, 1004, 1, CURRENT_TIMESTAMP(6), NULL, NULL, TRUE),
    (5, 1005, 1, CURRENT_TIMESTAMP(6), NULL, NULL, TRUE),
    (6, 1006, 1, CURRENT_TIMESTAMP(6), NULL, NULL, TRUE),
    (7, 1007, 1, CURRENT_TIMESTAMP(6), NULL, NULL, TRUE),
    (8, 1008, 1, CURRENT_TIMESTAMP(6), NULL, NULL, TRUE),
    (9, 1009, 1, CURRENT_TIMESTAMP(6), NULL, NULL, TRUE),
    (10, 1010, 1, CURRENT_TIMESTAMP(6), NULL, NULL, TRUE);
