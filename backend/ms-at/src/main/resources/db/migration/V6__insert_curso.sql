INSERT INTO curso (
    id,
    formacao_id,
    nome,
    instituicao,
    carga_horaria,
    criado_por,
    criado_em,
    atualizado_por,
    atualizado_em,
    ativo
) VALUES
(1, 1, 'Análise do Comportamento Aplicada (ABA)', 'Instituto Singular', 360, 1, CURRENT_TIMESTAMP(6), NULL, NULL, TRUE),
(2, 2, 'Integração Sensorial', 'Instituto de Terapia Ocupacional', 240, 1, CURRENT_TIMESTAMP(6), NULL, NULL, TRUE),
(3, 3, 'Neuropsicopedagogia Clínica', 'Faculdade FAVENI', 420, 1, CURRENT_TIMESTAMP(6), NULL, NULL, TRUE),
(4, 4, 'Estimulação Precoce', 'Instituto Brasileiro de Reabilitação', 180, 1, CURRENT_TIMESTAMP(6), NULL, NULL, TRUE),
(5, 5, 'Educação Inclusiva', 'Instituto Federal de Educação', 200, 1, CURRENT_TIMESTAMP(6), NULL, NULL, TRUE),
(6, 6, 'Intervenção em TEA', 'CBI of Miami', 300, 1, CURRENT_TIMESTAMP(6), NULL, NULL, TRUE),
(7, 7, 'Psicomotricidade', 'Universidade Estácio', 160, 1, CURRENT_TIMESTAMP(6), NULL, NULL, TRUE),
(8, 8, 'Comunicação Alternativa', 'Instituto Pró-Fono', 120, 1, CURRENT_TIMESTAMP(6), NULL, NULL, TRUE),
(9, 9, 'Reabilitação Cognitiva', 'Albert Einstein Ensino', 220, 1, CURRENT_TIMESTAMP(6), NULL, NULL, TRUE),
(10, 10, 'Manejo Comportamental', 'Instituto NeuroSaber', 280, 1, CURRENT_TIMESTAMP(6), NULL, NULL, TRUE);

