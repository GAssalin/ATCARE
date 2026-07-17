INSERT INTO usuario (id, pessoa_id, password, email, email_verificado, criado_por, criado_em, ativo) VALUES
(1, 1, '$2a$10$LAnWRm2Abm4HN0b.cYnUQeu7Tf5DbGeI6BzueIFsdJF6Bv7eb8vzq', 'teste@teste.com', TRUE, 1, CURRENT_TIMESTAMP, TRUE),
(2, 2, '$2a$10$exemploHashSenhaUsuario000000000000000000000002', 'bruno.lima@example.com', TRUE, 1, CURRENT_TIMESTAMP, TRUE),
(3, 3, '$2a$10$exemploHashSenhaUsuario000000000000000000000003', 'carla.mendes@example.com', FALSE, 1, CURRENT_TIMESTAMP, TRUE),
(4, 4, '$2a$10$exemploHashSenhaUsuario000000000000000000000004', 'diego.alves@example.com', TRUE, 1, CURRENT_TIMESTAMP, TRUE),
(5, 5, '$2a$10$exemploHashSenhaUsuario000000000000000000000005', 'elisa.rocha@example.com', FALSE, 1, CURRENT_TIMESTAMP, TRUE),
(6, 6, '$2a$10$exemploHashSenhaUsuario000000000000000000000006', 'felipe.martins@example.com', TRUE, 1, CURRENT_TIMESTAMP, TRUE),
(7, 7, '$2a$10$exemploHashSenhaUsuario000000000000000000000007', 'gabriela.nunes@example.com', FALSE, 1, CURRENT_TIMESTAMP, TRUE),
(8, 8, '$2a$10$exemploHashSenhaUsuario000000000000000000000008', 'henrique.costa@example.com', TRUE, 1, CURRENT_TIMESTAMP, TRUE),
(9, 9, '$2a$10$exemploHashSenhaUsuario000000000000000000000009', 'isabela.ferreira@example.com', FALSE, 1, CURRENT_TIMESTAMP, TRUE),
(10, 10, '$2a$10$exemploHashSenhaUsuario000000000000000000000010', 'joao.ribeiro@example.com', TRUE, 1, CURRENT_TIMESTAMP, TRUE);

ALTER TABLE usuario AUTO_INCREMENT = 11;
