# Sistema de Alocação de Recursos Computacionais (SARC)

Este repositório contém o **Sistema de Alocação de Recursos Computacionais (SARC)**, desenvolvido como parte do **Trabalho 1 da disciplina de Projeto e Arquitetura de Software (2024/II)** da **PUCRS/Escola Politécnica**.

O objetivo deste projeto é implementar um backend para gerenciar a reserva de recursos computacionais e auxiliar na modernização do sistema atual, inicialmente criado em 1996. Este sistema será uma base para transição futura para uma arquitetura de microserviços.

## 📚 Funcionalidades

O sistema suporta as seguintes operações:

1. **Gestão de Dados**
   - Cadastrar, editar e listar disciplinas, turmas, professores, e recursos.
   - Visualizar compromissos de uma turma.
   - Listar reservas de recursos para uma data específica.

2. **Gestão de Reservas**
   - Realizar reservas de recursos para compromissos planejados no semestre.
   - Garantir que regras de alocação sejam respeitadas (ex.: evitar conflitos de horários).

3. **Inicialização**
   - Script de inicialização para preencher o banco de dados com dados de teste:
     - **10 turmas**
     - **5 professores**
     - **5 reservas válidas**

## 🚀 Tecnologias e Arquitetura

### Framework e Ferramentas
- **Spring Boot** para desenvolvimento do backend.
- **Spring Data JPA** para gerenciar a persistência.
- **Swagger** para documentar e testar os endpoints REST.
- **BDD Tools** (Behavior-Driven Development) para associar histórias de usuário ao desenvolvimento.

### Arquitetura
O backend segue os princípios de **Domain-Driven Design (DDD)** e utiliza uma abordagem monolítica com código estruturado para facilitar a futura migração para microserviços.

### Entidades Principais
- **Disciplina**: Identificada por um código único e associada a uma carga horária.
- **Turma**: Relacionada a uma disciplina, com horários e um professor responsável.
- **Professor**: Contém dados de identificação e contato.
- **Recurso**: Sala de aula ou equipamento associado a compromissos.
- **Reserva**: Relaciona compromissos (aulas) com recursos disponíveis.

### Regras de Negócio
- Um recurso não pode estar associado a mais de uma aula no mesmo horário.
- Aulas ocorrem entre datas definidas para o semestre.
- Professores e turmas não podem ter conflitos de horário.

## 🌐 Endpoints REST

A API segue padrões RESTful e utiliza rotas e nomenclaturas em inglês. Um exemplo básico:

### Exemplos de Endpoints
1. 

Para mais detalhes, consulte a [documentação Swagger](#).

## 📦 Como Executar

1. Clone este repositório:
   ```bash
   git clone https://github.com/seuprojeto/sarc-backend.git
   cd sarc-backend
