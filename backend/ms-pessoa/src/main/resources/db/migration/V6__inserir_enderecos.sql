INSERT INTO endereco (id, pessoa_id, tipo_endereco, logradouro, numero, complemento, bairro, municipio_id, cep, principal, criado_por, criado_em, ativo) VALUES
(1, 1, 'RESIDENCIAL', 'Rua das Flores', '101', 'Apto 12', 'Centro', 1, '01001000', TRUE, 1, CURRENT_TIMESTAMP, TRUE),
(2, 2, 'RESIDENCIAL', 'Avenida Brasil', '250', NULL, 'Copacabana', 2, '22041001', TRUE, 1, CURRENT_TIMESTAMP, TRUE),
(3, 3, 'RESIDENCIAL', 'Rua da Bahia', '330', 'Casa 2', 'Lourdes', 3, '30160011', TRUE, 1, CURRENT_TIMESTAMP, TRUE),
(4, 4, 'COMERCIAL', 'Rua XV de Novembro', '44', 'Sala 5', 'Centro', 4, '80020310', TRUE, 1, CURRENT_TIMESTAMP, TRUE),
(5, 5, 'RESIDENCIAL', 'Avenida Ipiranga', '900', NULL, 'Praia de Belas', 5, '90160093', TRUE, 1, CURRENT_TIMESTAMP, TRUE),
(6, 11, 'COMERCIAL', 'Avenida Paulista', '1500', 'Conjunto 81', 'Bela Vista', 1, '01310200', TRUE, 1, CURRENT_TIMESTAMP, TRUE),
(7, 12, 'COMERCIAL', 'Rua Chile', '20', NULL, 'Centro', 6, '40020000', TRUE, 1, CURRENT_TIMESTAMP, TRUE),
(8, 13, 'COMERCIAL', 'Avenida Boa Viagem', '700', 'Sala 3', 'Boa Viagem', 7, '51011000', TRUE, 1, CURRENT_TIMESTAMP, TRUE),
(9, 14, 'COMERCIAL', 'Avenida Beira Mar', '1250', NULL, 'Meireles', 8, '60165121', TRUE, 1, CURRENT_TIMESTAMP, TRUE),
(10, 15, 'ENTREGA', 'Avenida Anhanguera', '3100', 'Bloco B', 'Setor Central', 9, '74043011', TRUE, 1, CURRENT_TIMESTAMP, TRUE);

ALTER TABLE endereco AUTO_INCREMENT = 11;
