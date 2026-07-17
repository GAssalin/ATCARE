INSERT INTO contato (id, pessoa_id, tipo_contato, valor, principal, criado_por, criado_em, ativo) VALUES
(1, 1, 'EMAIL', 'ana.souza@example.com', TRUE, 1, CURRENT_TIMESTAMP, TRUE),
(2, 2, 'CELULAR', '11990000002', TRUE, 1, CURRENT_TIMESTAMP, TRUE),
(3, 3, 'EMAIL', 'carla.mendes@example.com', TRUE, 1, CURRENT_TIMESTAMP, TRUE),
(4, 4, 'TELEFONE', '4133000004', TRUE, 1, CURRENT_TIMESTAMP, TRUE),
(5, 5, 'CELULAR', '51990000005', TRUE, 1, CURRENT_TIMESTAMP, TRUE),
(6, 11, 'EMAIL', 'contato@bemviver.example', TRUE, 1, CURRENT_TIMESTAMP, TRUE),
(7, 12, 'TELEFONE', '7133000007', TRUE, 1, CURRENT_TIMESTAMP, TRUE),
(8, 13, 'EMAIL', 'contato@integrar.example', TRUE, 1, CURRENT_TIMESTAMP, TRUE),
(9, 14, 'CELULAR', '85990000009', TRUE, 1, CURRENT_TIMESTAMP, TRUE),
(10, 15, 'EMAIL', 'contato@saudeplena.example', TRUE, 1, CURRENT_TIMESTAMP, TRUE);

ALTER TABLE contato AUTO_INCREMENT = 11;
