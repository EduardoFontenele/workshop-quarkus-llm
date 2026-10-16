CREATE DATABASE IF NOT EXISTS rh_workshop CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE rh_workshop;

CREATE TABLE funcionarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    email VARCHAR(120) NOT NULL UNIQUE,
    cargo VARCHAR(80) NOT NULL,
    departamento VARCHAR(80) NOT NULL,
    data_admissao DATE NOT NULL,
    telefone VARCHAR(20)
) ENGINE=InnoDB;

CREATE TABLE folha_pagamento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    funcionario_id BIGINT NOT NULL,
    mes_referencia DATE NOT NULL,
    salario_bruto DECIMAL(10,2) NOT NULL,
    desconto_inss DECIMAL(10,2) NOT NULL,
    desconto_irrf DECIMAL(10,2) NOT NULL,
    desconto_vale_transporte DECIMAL(10,2) NOT NULL,
    desconto_plano_saude DECIMAL(10,2) NOT NULL,
    salario_liquido DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_folha_funcionario FOREIGN KEY (funcionario_id) REFERENCES funcionarios(id) ON DELETE CASCADE,
    CONSTRAINT uq_folha_funcionario_mes UNIQUE (funcionario_id, mes_referencia)
) ENGINE=InnoDB;

-- 4 funcionários
INSERT INTO funcionarios (nome, cpf, email, cargo, departamento, data_admissao, telefone) VALUES
('Ana Beatriz Souza', '111.222.333-44', 'ana.souza@rhworkshop.com', 'Analista de RH', 'Recursos Humanos', '2021-03-15', '(11) 98888-1111'),
('Carlos Eduardo Lima', '222.333.444-55', 'carlos.lima@rhworkshop.com', 'Desenvolvedor Backend', 'Tecnologia', '2019-07-01', '(11) 98888-2222'),
('Mariana Oliveira Santos', '333.444.555-66', 'mariana.santos@rhworkshop.com', 'Analista Financeiro', 'Financeiro', '2022-01-10', '(11) 98888-3333'),
('Pedro Henrique Costa', '444.555.666-77', 'pedro.costa@rhworkshop.com', 'Assistente Administrativo', 'Administrativo', '2023-05-20', '(11) 98888-4444');

-- Histórico de folha de pagamento: últimos 6 meses (maio a outubro de 2026) para cada funcionário
INSERT INTO folha_pagamento (funcionario_id, mes_referencia, salario_bruto, desconto_inss, desconto_irrf, desconto_vale_transporte, desconto_plano_saude, salario_liquido) VALUES
-- Ana Beatriz Souza (id 1) - bruto 4500.00
(1, '2026-05-01', 4500.00, 495.00, 225.00, 90.00, 150.00, 3540.00),
(1, '2026-06-01', 4500.00, 495.00, 225.00, 90.00, 150.00, 3540.00),
(1, '2026-07-01', 4500.00, 495.00, 225.00, 90.00, 150.00, 3540.00),
(1, '2026-08-01', 4500.00, 495.00, 225.00, 90.00, 150.00, 3540.00),
(1, '2026-09-01', 4500.00, 495.00, 225.00, 90.00, 150.00, 3540.00),
(1, '2026-10-01', 4500.00, 495.00, 225.00, 90.00, 150.00, 3540.00),

-- Carlos Eduardo Lima (id 2) - bruto 8500.00
(2, '2026-05-01', 8500.00, 765.00, 1190.00, 170.00, 280.00, 6095.00),
(2, '2026-06-01', 8500.00, 765.00, 1190.00, 170.00, 280.00, 6095.00),
(2, '2026-07-01', 8500.00, 765.00, 1190.00, 170.00, 280.00, 6095.00),
(2, '2026-08-01', 8500.00, 765.00, 1190.00, 170.00, 280.00, 6095.00),
(2, '2026-09-01', 8500.00, 765.00, 1190.00, 170.00, 280.00, 6095.00),
(2, '2026-10-01', 8500.00, 765.00, 1190.00, 170.00, 280.00, 6095.00),

-- Mariana Oliveira Santos (id 3) - bruto 6200.00
(3, '2026-05-01', 6200.00, 608.00, 558.00, 124.00, 200.00, 4710.00),
(3, '2026-06-01', 6200.00, 608.00, 558.00, 124.00, 200.00, 4710.00),
(3, '2026-07-01', 6200.00, 608.00, 558.00, 124.00, 200.00, 4710.00),
(3, '2026-08-01', 6200.00, 608.00, 558.00, 124.00, 200.00, 4710.00),
(3, '2026-09-01', 6200.00, 608.00, 558.00, 124.00, 200.00, 4710.00),
(3, '2026-10-01', 6200.00, 608.00, 558.00, 124.00, 200.00, 4710.00),

-- Pedro Henrique Costa (id 4) - bruto 3200.00
(4, '2026-05-01', 3200.00, 352.00, 0.00, 64.00, 120.00, 2664.00),
(4, '2026-06-01', 3200.00, 352.00, 0.00, 64.00, 120.00, 2664.00),
(4, '2026-07-01', 3200.00, 352.00, 0.00, 64.00, 120.00, 2664.00),
(4, '2026-08-01', 3200.00, 352.00, 0.00, 64.00, 120.00, 2664.00),
(4, '2026-09-01', 3200.00, 352.00, 0.00, 64.00, 120.00, 2664.00),
(4, '2026-10-01', 3200.00, 352.00, 0.00, 64.00, 120.00, 2664.00);
