INSERT INTO municipio (id, nome, uf, codigo_ibge, criado_por, criado_em, ativo) VALUES
(1, 'Sao Paulo', 'SP', '3550308', 1, CURRENT_TIMESTAMP, TRUE),
(2, 'Rio de Janeiro', 'RJ', '3304557', 1, CURRENT_TIMESTAMP, TRUE),
(3, 'Belo Horizonte', 'MG', '3106200', 1, CURRENT_TIMESTAMP, TRUE),
(4, 'Curitiba', 'PR', '4106902', 1, CURRENT_TIMESTAMP, TRUE),
(5, 'Porto Alegre', 'RS', '4314902', 1, CURRENT_TIMESTAMP, TRUE),
(6, 'Salvador', 'BA', '2927408', 1, CURRENT_TIMESTAMP, TRUE),
(7, 'Recife', 'PE', '2611606', 1, CURRENT_TIMESTAMP, TRUE),
(8, 'Fortaleza', 'CE', '2304400', 1, CURRENT_TIMESTAMP, TRUE),
(9, 'Goiania', 'GO', '5208707', 1, CURRENT_TIMESTAMP, TRUE),
(10, 'Brasilia', 'DF', '5300108', 1, CURRENT_TIMESTAMP, TRUE);

ALTER TABLE municipio AUTO_INCREMENT = 11;
