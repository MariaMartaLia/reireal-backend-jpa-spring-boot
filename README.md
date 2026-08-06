# ReiReal API

API REST desenvolvida com **Java 21** e **Spring Boot** para gerenciamento de uma adega, contemplando o cadastro de clientes, categorias, produtos, pedidos e suas respectivas regras de negócio.

O projeto foi desenvolvido com foco na construção de uma aplicação Backend organizada, aplicando arquitetura em camadas, modelagem de domínio, persistência com Spring Data JPA e boas práticas de Engenharia de Software utilizadas em aplicações corporativas.

Seu desenvolvimento é contínuo, com novas funcionalidades sendo incorporadas conforme a evolução do domínio da aplicação.

---

# Visão Geral

O ReiReal simula um sistema de gestão comercial para adegas, permitindo o gerenciamento das principais entidades envolvidas no processo de vendas.

### Fluxo principal

```

Cliente
↓
Pedido
↓
Itens do Pedido
↓
Produto
↓
Categoria
↓
Fechamento (em desenvolvimento)

```

---

# Tecnologias

## Backend

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate

## Banco de Dados

- PostgreSQL

## Build

- Maven

## Versionamento

- Git
- GitHub

## Em evolução

- Docker
- Swagger/OpenAPI

---

# Arquitetura

A aplicação foi estruturada seguindo o padrão de **Arquitetura em Camadas**, separando responsabilidades entre as diferentes partes do sistema.

```

Controller
↓
Service
↓
Repository
↓
PostgreSQL

```

Essa organização favorece:

- separação de responsabilidades;
- manutenção do código;
- reutilização de componentes;
- escalabilidade;
- evolução contínua da aplicação.

---

# Funcionalidades

## Clientes

- Cadastro
- Consulta
- Atualização
- Exclusão

---

## Categorias

- Cadastro
- Consulta
- Atualização
- Exclusão

---

## Produtos

- Cadastro
- Consulta
- Atualização
- Exclusão
- Associação com Categorias

---

## Pedidos

- Cadastro
- Consulta
- Atualização
- Exclusão
- Associação com Clientes
- Associação com Itens do Pedido

---

## Itens do Pedido

- Cadastro
- Consulta
- Atualização
- Exclusão
- Associação com Produtos

---

## Fechamento

🚧 Em desenvolvimento

---

# Conceitos aplicados

- Programação Orientada a Objetos
- Arquitetura em Camadas
- Modelagem de Domínio
- Spring Data JPA
- Hibernate
- Repository Pattern
- Service Layer Pattern
- DTO Request / Response
- Bean Validation
- Injeção de Dependência
- Relacionamentos JPA
- Conversão Entity ↔ DTO
- Tratamento de Exceções
- Regras de Negócio

---

# Estrutura do Projeto

```

src
├── controller
├── service
├── repository
├── domain
│ ├── entity
│ └── enums
├── dto
│ ├── request
│ └── response
└── config

```

Cada camada possui uma responsabilidade específica, contribuindo para uma estrutura organizada e de fácil manutenção.

---

# Persistência

O projeto utiliza **PostgreSQL** integrado ao **Spring Data JPA** e **Hibernate** para persistência dos dados.

Relacionamentos implementados:

- Categoria → Produtos
- Cliente → Pedidos
- Pedido → Itens do Pedido
- Produto → Item do Pedido

---

# Endpoints

Os principais recursos disponibilizados pela API incluem:

```

GET    /clientes
POST   /clientes

GET    /categorias
POST   /categorias

GET    /produtos
POST   /produtos

GET    /pedidos
POST   /pedidos

```

*A documentação completa da API será disponibilizada via Swagger.*

---

# Roadmap

Próximas evoluções planejadas para o projeto:

- Implementação do módulo de Fechamento
- Docker
- Swagger/OpenAPI
- Spring Security + JWT
- Tratamento global de exceções
- Paginação
- Testes Unitários
- Documentação completa da API

---

# Objetivo

O ReiReal foi desenvolvido para reproduzir práticas utilizadas em aplicações Backend corporativas, aplicando conceitos de Engenharia de Software, arquitetura em camadas, modelagem de domínio e construção de APIs REST.

Além da implementação das funcionalidades, o projeto prioriza organização, legibilidade, manutenção e evolução contínua da aplicação.

---

# Diferenciais

- Arquitetura em Camadas
- Separação clara de responsabilidades
- Modelagem de domínio
- Persistência com Spring Data JPA
- Relacionamentos entre entidades
- DTOs para comunicação da API
- Regras de negócio centralizadas na camada de serviços
- Código estruturado para facilitar manutenção e evolução

---

# Como executar

### Clone o projeto

```bash
git clone https://github.com/MariaMartaLia/reireal-backend-jpa-spring-boot.git
```

### Entre na pasta

```bash
cd reireal-backend-jpa-spring-boot
```

### Configure o banco PostgreSQL

Edite o arquivo:

```
src/main/resources/application.properties
```

Configure:

```
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
```

### Execute a aplicação

```bash
mvn spring-boot:run
```

---

# Próximos passos

Após a conclusão das funcionalidades previstas, serão adicionados:

- documentação Swagger;
- diagrama de entidades;
- DER do banco de dados;
- exemplos de requisições;
- imagens da API em execução;
- GIF demonstrando o funcionamento da aplicação.

---

# Autora

**Maria Marta Almeida Brito**

Desenvolvedora Backend Java

🔗 LinkedIn  
https://www.linkedin.com/in/mariamartabrito

🔗 GitHub  
https://github.com/MariaMartaLia
