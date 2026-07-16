INSERT INTO validacao (
    id,
    certificado_id,
    status,
    observacao,
    data_validacao,
    usuario_validador,
    criado_por,
    criado_em,
    atualizado_por,
    atualizado_em,
    ativo
) VALUES
(1,1,'APROVADO','Certificado validado com sucesso.',CURRENT_TIMESTAMP(6),1,1,CURRENT_TIMESTAMP(6),NULL,NULL,TRUE),
(2,2,'APROVADO','Documentação conferida.',CURRENT_TIMESTAMP(6),1,1,CURRENT_TIMESTAMP(6),NULL,NULL,TRUE),
(3,3,'REPROVADO','Arquivo ilegível.',CURRENT_TIMESTAMP(6),2,1,CURRENT_TIMESTAMP(6),NULL,NULL,TRUE),
(4,4,'APROVADO','Validação concluída.',CURRENT_TIMESTAMP(6),2,1,CURRENT_TIMESTAMP(6),NULL,NULL,TRUE),
(5,5,'APROVADO','Curso reconhecido.',CURRENT_TIMESTAMP(6),3,1,CURRENT_TIMESTAMP(6),NULL,NULL,TRUE),
(6,6,'REPROVADO','Necessário reenviar certificado.',CURRENT_TIMESTAMP(6),3,1,CURRENT_TIMESTAMP(6),NULL,NULL,TRUE),
(7,7,'APROVADO','Documentação aprovada.',CURRENT_TIMESTAMP(6),4,1,CURRENT_TIMESTAMP(6),NULL,NULL,TRUE),
(8,8,'APROVADO','Certificado compatível.',CURRENT_TIMESTAMP(6),4,1,CURRENT_TIMESTAMP(6),NULL,NULL,TRUE),
(9,9,'APROVADO','Validação automática.',CURRENT_TIMESTAMP(6),5,1,CURRENT_TIMESTAMP(6),NULL,NULL,TRUE),
(10,10,'REPROVADO','Documento incompleto.',CURRENT_TIMESTAMP(6),5,1,CURRENT_TIMESTAMP(6),NULL,NULL,TRUE);

