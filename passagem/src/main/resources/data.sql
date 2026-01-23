CREATE TABLE voo (
                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                     codigo VARCHAR(20) NOT NULL,        -- Ex: 'GOL1234'
                     origem VARCHAR(50) NOT NULL,        -- Ex: 'GRU'
                     destino VARCHAR(50) NOT NULL,       -- Ex: 'GIG'
                     data_hora TIMESTAMP NOT NULL,       -- Data da partida
                     preco DECIMAL(10, 2) NOT NULL,      -- Preço unitário
                     assentos_totais INT NOT NULL,       -- Capacidade
                     assentos_disponiveis INT NOT NULL   -- Controle de estoque
);

-- 2. Tabela de Passagens (O lado "Muitos" da relação)
CREATE TABLE passagem (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          voo_id BIGINT NOT NULL,
                          user_id BIGINT NOT NULL,
                          nome_passageiro VARCHAR(255) NOT NULL,
                          email_passageiro VARCHAR(255) NOT NULL,
                          data_compra TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (voo_id) REFERENCES voo(id)
);