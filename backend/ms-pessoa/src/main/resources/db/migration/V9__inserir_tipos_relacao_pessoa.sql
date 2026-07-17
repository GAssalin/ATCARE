INSERT INTO tipo_relacao_pessoa (id, nome, descricao, criado_por, criado_em, ativo) VALUES
(1, 'Pai', 'Pai da pessoa relacionada', 1, CURRENT_TIMESTAMP, TRUE),
(2, 'Mae', 'Mae da pessoa relacionada', 1, CURRENT_TIMESTAMP, TRUE),
(3, 'Filho', 'Filho ou filha da pessoa relacionada', 1, CURRENT_TIMESTAMP, TRUE),
(4, 'Irmao', 'Irmao ou irma da pessoa relacionada', 1, CURRENT_TIMESTAMP, TRUE),
(5, 'Conjuge', 'Conjuge ou companheiro', 1, CURRENT_TIMESTAMP, TRUE),
(6, 'Responsavel legal', 'Responsavel legal da pessoa', 1, CURRENT_TIMESTAMP, TRUE),
(7, 'Tutor', 'Tutor legal da pessoa', 1, CURRENT_TIMESTAMP, TRUE),
(8, 'Dependente', 'Dependente da pessoa', 1, CURRENT_TIMESTAMP, TRUE),
(9, 'Socio', 'Socio de pessoa juridica', 1, CURRENT_TIMESTAMP, TRUE),
(10, 'Responsavel financeiro', 'Responsavel pelos compromissos financeiros', 1, CURRENT_TIMESTAMP, TRUE);

ALTER TABLE tipo_relacao_pessoa AUTO_INCREMENT = 11;
