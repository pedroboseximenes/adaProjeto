INSERT INTO voo (codigo, origem, destino, data_hora, preco, assentos_totais, assentos_disponiveis)
VALUES
-- Voo 1: São Paulo -> Rio (Preço padrão, muitos lugares)
('G3-1234', 'SÃO PAULO', 'RIO DE JANEIRO', '2026-05-10 08:00:00', 450.00, 180, 150),

-- Voo 2: Brasília -> Salvador (Voo mais caro, quase lotado)
('LA-4500', 'BRASILIA', 'SALVADOR', '2026-05-12 14:30:00', 890.50, 160, 5),

-- Voo 3: Campinas -> Belo Horizonte (Voo barato, lotação média)
('AD-2020', 'CAMPINAS', 'BELO HORIZONTE', '2026-05-15 09:15:00', 230.90, 118, 60),

-- Voo 4: Recife -> Fortaleza (Voo noturno, vazio)
('VOE-99', 'RECIFE', 'FORTALEZA', '2026-05-20 23:00:00', 380.00, 150, 150);
