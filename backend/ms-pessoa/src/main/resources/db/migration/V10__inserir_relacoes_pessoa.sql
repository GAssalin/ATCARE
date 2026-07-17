INSERT INTO pessoa_relacao (id, pessoa_id, relacionado_id, tipo_relacao_pessoa_id, criado_por, criado_em, ativo) VALUES
(1, 1, 2, 5, 1, CURRENT_TIMESTAMP, TRUE),
(2, 2, 1, 5, 1, CURRENT_TIMESTAMP, TRUE),
(3, 3, 4, 4, 1, CURRENT_TIMESTAMP, TRUE),
(4, 4, 3, 4, 1, CURRENT_TIMESTAMP, TRUE),
(5, 5, 6, 6, 1, CURRENT_TIMESTAMP, TRUE),
(6, 6, 7, 1, 1, CURRENT_TIMESTAMP, TRUE),
(7, 7, 6, 3, 1, CURRENT_TIMESTAMP, TRUE),
(8, 8, 9, 10, 1, CURRENT_TIMESTAMP, TRUE),
(9, 11, 10, 9, 1, CURRENT_TIMESTAMP, TRUE),
(10, 12, 8, 6, 1, CURRENT_TIMESTAMP, TRUE);

ALTER TABLE pessoa_relacao AUTO_INCREMENT = 11;
