INSERT INTO especialidade (
    id,
    nome,
    descricao,
    criado_por,
    criado_em,
    atualizado_por,
    atualizado_em,
    ativo
) VALUES
(1, 'Autismo (TEA)', 'Atendimento especializado para pessoas com Transtorno do Espectro Autista', 1, CURRENT_TIMESTAMP(6), NULL, NULL, TRUE),
(2, 'Síndrome de Down', 'Acompanhamento terapêutico para pessoas com Síndrome de Down', 1, CURRENT_TIMESTAMP(6), NULL, NULL, TRUE),
(3, 'Paralisia Cerebral', 'Suporte terapêutico para pessoas com paralisia cerebral', 1, CURRENT_TIMESTAMP(6), NULL, NULL, TRUE),
(4, 'Deficiência Intelectual', 'Intervenções para desenvolvimento cognitivo e autonomia', 1, CURRENT_TIMESTAMP(6), NULL, NULL, TRUE),
(5, 'TDAH', 'Acompanhamento para pessoas com Transtorno do Déficit de Atenção e Hiperatividade', 1, CURRENT_TIMESTAMP(6), NULL, NULL, TRUE),
(6, 'Transtornos de Ansiedade', 'Acompanhamento terapêutico para manejo da ansiedade', 1, CURRENT_TIMESTAMP(6), NULL, NULL, TRUE),
(7, 'Estimulação Precoce', 'Intervenção em crianças na primeira infância', 1, CURRENT_TIMESTAMP(6), NULL, NULL, TRUE),
(8, 'Reabilitação Neurológica', 'Acompanhamento de pacientes em processo de reabilitação', 1, CURRENT_TIMESTAMP(6), NULL, NULL, TRUE),
(9, 'Inclusão Escolar', 'Acompanhamento de alunos no ambiente escolar', 1, CURRENT_TIMESTAMP(6), NULL, NULL, TRUE),
(10, 'Idosos', 'Acompanhamento terapêutico voltado ao público idoso', 1, CURRENT_TIMESTAMP(6), NULL, NULL, TRUE);

