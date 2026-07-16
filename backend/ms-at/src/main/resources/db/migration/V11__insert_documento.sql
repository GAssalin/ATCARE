INSERT INTO at_documento (
    id,
    at_id,
    nome,
    tipo,
    arquivo_comprovante,
    observacao,
    criado_por,
    criado_em,
    atualizado_por,
    atualizado_em,
    ativo
) VALUES
(1,1,'RG','RG','documentos/rg_001.pdf','Documento oficial.',1,CURRENT_TIMESTAMP(6),NULL,NULL,TRUE),
(2,2,'CPF','CPF','documentos/cpf_002.pdf','Documento oficial.',1,CURRENT_TIMESTAMP(6),NULL,NULL,TRUE),
(3,3,'Comprovante de Residência','COMPROVANTE_RESIDENCIA','documentos/residencia_003.pdf','Atualizado.',1,CURRENT_TIMESTAMP(6),NULL,NULL,TRUE),
(4,4,'Currículo','CURRICULO','documentos/curriculo_004.pdf','Versão atual.',1,CURRENT_TIMESTAMP(6),NULL,NULL,TRUE),
(5,5,'CRP','REGISTRO_PROFISSIONAL','documentos/crp_005.pdf','Registro profissional.',1,CURRENT_TIMESTAMP(6),NULL,NULL,TRUE),
(6,6,'CREFITO','REGISTRO_PROFISSIONAL','documentos/crefito_006.pdf','Registro válido.',1,CURRENT_TIMESTAMP(6),NULL,NULL,TRUE),
(7,7,'Diploma','DIPLOMA','documentos/diploma_007.pdf','Ensino superior.',1,CURRENT_TIMESTAMP(6),NULL,NULL,TRUE),
(8,8,'CNH','CNH','documentos/cnh_008.pdf','Categoria B.',1,CURRENT_TIMESTAMP(6),NULL,NULL,TRUE),
(9,9,'Foto Perfil','FOTO_PERFIL','documentos/foto_009.jpg','Foto recente.',1,CURRENT_TIMESTAMP(6),NULL,NULL,TRUE),
(10,10,'Carteira Profissional','CARTEIRA_PROFISSIONAL','documentos/carteira_010.pdf','Documento válido.',1,CURRENT_TIMESTAMP(6),NULL,NULL,TRUE);

