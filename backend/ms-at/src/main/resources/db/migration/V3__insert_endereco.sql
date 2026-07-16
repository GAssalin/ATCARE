INSERT INTO endereco (
    id,
    cep,
    logradouro,
    numero,
    complemento,
    bairro,
    cidade,
    estado,
    criado_por,
    criado_em,
    atualizado_por,
    atualizado_em,
    ativo
) VALUES
(1,'01310-100','Avenida Paulista','1000','Sala 101','Bela Vista','São Paulo','SP',1,CURRENT_TIMESTAMP(6),NULL,NULL,TRUE),
(2,'20040-020','Rua da Assembleia','250',NULL,'Centro','Rio de Janeiro','RJ',1,CURRENT_TIMESTAMP(6),NULL,NULL,TRUE),
(3,'30130-110','Avenida Afonso Pena','1500',NULL,'Centro','Belo Horizonte','MG',1,CURRENT_TIMESTAMP(6),NULL,NULL,TRUE),
(4,'80010-000','Rua XV de Novembro','450','Loja 02','Centro','Curitiba','PR',1,CURRENT_TIMESTAMP(6),NULL,NULL,TRUE),
(5,'90020-090','Rua dos Andradas','850',NULL,'Centro Histórico','Porto Alegre','RS',1,CURRENT_TIMESTAMP(6),NULL,NULL,TRUE),
(6,'70040-010','SQS 308 Bloco A','15','Apto 302','Asa Sul','Brasília','DF',1,CURRENT_TIMESTAMP(6),NULL,NULL,TRUE),
(7,'40020-000','Avenida Sete de Setembro','1800',NULL,'Campo Grande','Salvador','BA',1,CURRENT_TIMESTAMP(6),NULL,NULL,TRUE),
(8,'60160-230','Avenida Dom Luís','980','Sala 405','Aldeota','Fortaleza','CE',1,CURRENT_TIMESTAMP(6),NULL,NULL,TRUE),
(9,'69005-040','Rua 10 de Julho','600',NULL,'Centro','Manaus','AM',1,CURRENT_TIMESTAMP(6),NULL,NULL,TRUE),
(10,'88010-400','Rua Felipe Schmidt','320',NULL,'Centro','Florianópolis','SC',1,CURRENT_TIMESTAMP(6),NULL,NULL,TRUE);

