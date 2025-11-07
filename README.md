# ⌚ RELOJOARIA DIGITAL

API REST para gestão de vendas e serviços de uma relojoaria.  
Permite controlar clientes, ordens de serviço, estoque e produtos, mantendo o fluxo completo de atendimento.

## 🛠️ Ferramentas

- Java 21
- Spring Boot 3.5.6
- PostgresSQL
- Docker

## ✨ Funcionalidades

- Sistema CRUD
- Criação de Ordem de Seviços:
  - Status: `TODO`, `IN PROGRESS`, `DONE`
  - Type: `SALE`, `REPAIR`
  - Dependências: Client e Product
- Atualização de estoque conforme o uso de produtos nos serviços
- Adição de sub-serviços

### Fluxo de criação da Ordem de Serviço
```
Início
 └── Receber requisição
     └── Validar dados
         ├── Inválido → Lançar Exceção → Fim
         └── Válido
             └── Verificar cliente
                 ├── Não existe → Lançar Exceção → Fim
                 └── Existe
                     └── Criar Ordem de Serviço base
                         └── Processar estoque dos itens requisitados
                             ├── Verificar quantidade disponível
                             ├── Calcular valor dos itens
                             └── Atualizar estoque
                         └── Calcular valor total da Ordem
                             ├── Sub-Serviços + Produtos + Valor adicional
                             └── Salvar → Fim
```

## 📒 Banco de Dados
- URL: `jdbc:postgresql://localhost:5434/relojoaria`
- Password: `root`, User: `postgres`
### Entidades:
- Client
- Product
- ServiceOrder
- Stock
- SubService
- MaterialUsage

### Relacionamentos:
| Entidade | Tipo de relação | Relacionada com |
|-----------|-----------------|----------------|
| Client | 1:N | ServiceOrder |
| ServiceOrder | 1:N | SubService |
| Stock | 1:1 | Product |
| MaterialUsage | N:1 | Product |

## ⚙️ Como executar o projeto

### Usando o Docker
Com o Docker instalado em sua máquina execute o seguinte comando na raiz do projeto
```bash
  doker compose up --build
```
A interface de requisições utilizando o swagger pode ser acessada pelo link: [swagger-ui](http://localhost:8080/swagger-ui)
## ⏳ TODO

- Personalizar os sub-serviços para tarefas que podem não estar relacionadas ao uso de produtos do estoque
- Implementar autenticação e autorização
- Implementar configurações de segurança