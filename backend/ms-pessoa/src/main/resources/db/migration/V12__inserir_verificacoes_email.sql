INSERT INTO usuario_email_verificacao (id, usuario_id, token_hash, expiracao, utilizado, criado_por, criado_em, ativo) VALUES
(1, 1, 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa1', DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 1 DAY), FALSE, 1, CURRENT_TIMESTAMP, TRUE),
(2, 2, 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa2', DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 1 DAY), TRUE, 1, CURRENT_TIMESTAMP, TRUE),
(3, 3, 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa3', DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 2 DAY), FALSE, 1, CURRENT_TIMESTAMP, TRUE),
(4, 4, 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa4', DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 2 DAY), TRUE, 1, CURRENT_TIMESTAMP, TRUE),
(5, 5, 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa5', DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 3 DAY), FALSE, 1, CURRENT_TIMESTAMP, TRUE),
(6, 6, 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa6', DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 3 DAY), TRUE, 1, CURRENT_TIMESTAMP, TRUE),
(7, 7, 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa7', DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 4 DAY), FALSE, 1, CURRENT_TIMESTAMP, TRUE),
(8, 8, 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa8', DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 4 DAY), TRUE, 1, CURRENT_TIMESTAMP, TRUE),
(9, 9, 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa9', DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 5 DAY), FALSE, 1, CURRENT_TIMESTAMP, TRUE),
(10, 10, 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa10', DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 5 DAY), TRUE, 1, CURRENT_TIMESTAMP, TRUE);

ALTER TABLE usuario_email_verificacao AUTO_INCREMENT = 11;
