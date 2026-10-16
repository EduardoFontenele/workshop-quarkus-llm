# RH Workshop — API Quarkus

API para o workshop de Quarkus + integração com LLM da semana acadêmica, com tema de departamento de RH. O projeto contrasta duas abordagens: uma **API REST clássica** (CRUD de funcionários + consulta de folha de pagamento) e um **endpoint com LLM e tool calling**, que usa as mesmas regras de negócio por trás de linguagem natural.

## Stack

- **Quarkus** 3.33.4 (LTS) + Java 21
- **Jakarta REST** (`quarkus-rest` + `quarkus-rest-jackson`)
- **Hibernate ORM with Panache** + driver **MySQL**
- **MySQL 8.4** via Docker (schema e dados mock criados por SQL puro)
- **Quarkus LangChain4j** (`quarkus-langchain4j-openai`) para o endpoint com LLM e tool calling

## Estrutura

```
workshop-quarkus-rh/
├── docker/
│   ├── docker-compose.yml   # sobe o MySQL já com schema e dados
│   ├── Dockerfile
│   └── init.sql             # schema + 4 funcionários + 24 registros de folha
├── src/main/java/com/rh/workshop/
│   ├── entity/               # Funcionario, FolhaPagamento
│   ├── dto/                  # FuncionarioRequest, FolhaPagamentoResponse
│   ├── service/               # FuncionarioService, FolhaPagamentoService
│   ├── ai/                    # RhTools (tool calling), RhAssistantService (AI Service)
│   └── resource/               # FuncionarioResource (CRUD), FolhaPagamentoResource, AssistenteResource
├── mvnw / mvnw.cmd
└── pom.xml
```

## Como rodar

### 1. Subir o banco MySQL

```bash
cd docker
docker compose up -d
```

Isso cria o banco `rh_workshop` já com as tabelas `funcionarios` e `folha_pagamento`, populadas com 4 funcionários e os últimos 6 meses de folha de pagamento de cada um.

### 2. Configurar a API key da OpenAI

O endpoint com LLM precisa da variável de ambiente `OPENAI_API_KEY` disponível no terminal onde a API vai rodar:

```bash
export OPENAI_API_KEY="sua-api-key-aqui"
```

### 3. Subir a API Quarkus

Na raiz do projeto:

```bash
./mvnw quarkus:dev
```

A API sobe em `http://localhost:8080`.

Para derrubar o banco depois:

```bash
cd docker
docker compose down -v
```

## Modelo de dados

**funcionarios**: `id`, `nome`, `cpf`, `email`, `cargo`, `departamento`, `data_admissao`, `telefone`

**folha_pagamento**: `id`, `funcionario_id`, `mes_referencia`, `salario_bruto`, `desconto_inss`, `desconto_irrf`, `desconto_vale_transporte`, `desconto_plano_saude`, `salario_liquido`

## Endpoints — CRUD de Funcionários

Base URL: `http://localhost:8080/api/funcionarios`

### Listar todos os funcionários

```bash
curl -s http://localhost:8080/api/funcionarios | jq
```

### Buscar funcionário por ID

```bash
curl -s http://localhost:8080/api/funcionarios/1 | jq
```

**Resposta 404** se o ID não existir:

```bash
curl -i http://localhost:8080/api/funcionarios/999
```

### Criar funcionário

```bash
curl -s -X POST http://localhost:8080/api/funcionarios \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Juliana Alves Pereira",
    "cpf": "555.666.777-88",
    "email": "juliana.pereira@rhworkshop.com",
    "cargo": "Analista de Recrutamento",
    "departamento": "Recursos Humanos",
    "dataAdmissao": "2026-10-01",
    "telefone": "(11) 90000-0000"
  }' | jq
```

### Atualizar funcionário

```bash
curl -s -X PUT http://localhost:8080/api/funcionarios/5 \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Juliana Alves Pereira",
    "cpf": "555.666.777-88",
    "email": "juliana.pereira@rhworkshop.com",
    "cargo": "Analista de Recrutamento Sr",
    "departamento": "Recursos Humanos",
    "dataAdmissao": "2026-10-01",
    "telefone": "(11) 90000-0001"
  }' | jq
```

### Excluir funcionário

```bash
curl -i -X DELETE http://localhost:8080/api/funcionarios/5
```

Resposta esperada: `204 No Content`.

## Endpoint — Folha de Pagamento por Funcionário

Base URL: `http://localhost:8080/api/funcionarios/{funcionarioId}/folha-pagamento`

### Buscar histórico de folha de pagamento de um funcionário

```bash
curl -s http://localhost:8080/api/funcionarios/1/folha-pagamento | jq
```

**Resposta 404** se o funcionário não existir:

```bash
curl -i http://localhost:8080/api/funcionarios/999/folha-pagamento
```

## Endpoint — Assistente com LLM e Tool Calling

Base URL: `http://localhost:8080/api/assistente`

Recebe **texto puro** (não JSON) e usa o LLM (OpenAI) com tool calling para consultar os dados reais de funcionários e folha de pagamento (via as mesmas tools/services da API REST) antes de responder.

### Perguntar algo em linguagem natural

```bash
curl -s -X POST http://localhost:8080/api/assistente \
  -H "Content-Type: text/plain" \
  --data "Qual o salário líquido do Carlos Eduardo Lima em outubro de 2026?"
```

### Outros exemplos de pergunta

```bash
curl -s -X POST http://localhost:8080/api/assistente \
  -H "Content-Type: text/plain" \
  --data "Quais funcionários estão cadastrados no departamento de Tecnologia?"
```

```bash
curl -s -X POST http://localhost:8080/api/assistente \
  -H "Content-Type: text/plain" \
  --data "Qual o desconto de INSS da Mariana Oliveira Santos nos últimos meses?"
```

Com `quarkus.langchain4j.openai.log-requests`/`log-responses` ativados em `application.properties`, o terminal onde a API está rodando mostra no log quais tools o modelo decidiu chamar — ótimo para demonstrar o tool calling ao vivo no workshop.

## Dados mock já disponíveis

| id | nome | cargo | departamento |
|----|------|-------|--------------|
| 1 | Ana Beatriz Souza | Analista de RH | Recursos Humanos |
| 2 | Carlos Eduardo Lima | Desenvolvedor Backend | Tecnologia |
| 3 | Mariana Oliveira Santos | Analista Financeiro | Financeiro |
| 4 | Pedro Henrique Costa | Assistente Administrativo | Administrativo |

Cada um já tem 6 meses de histórico de folha de pagamento (maio a outubro de 2026) na tabela `folha_pagamento`.
