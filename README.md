# CRUD CLIENTE API

## Objetivo

### Desenvolva uma REST API que:
- Permita criação de novos clientes;
- Permita a atualização de clientes existentes;
- Permita que seja possível listar os clientes de forma paginada;
- Permita que buscas por atributos cadastrais do cliente;
- É necessário também que cada elemento retornado pela api de clientes informe a idade;
- Documente sua API e também disponibilize um arquivo Postman para fácil utilização da API.

### Bônus 
 Para ir além das expectativas, desenvolva os itens abaixo, conforme sentir necessidade:
- Empacote sua aplicação com Docker;
- Forneça outros itens de infraestrutura em containers (Banco de Dados, Cache, etc);
- Utilize um orquestrador de container (Ex. Docker Compose);
- Hospede o projeto em algum provedor cloud e forneça por email a URL para acesso.

## Tecnologias Utilizadas

- Java 11
- Spring Boot 2.4.4
- Spring Data JPA 2.4.4
- Banco PostgeSQL
- Swagger 3.0.0
- Docker 20.10.5
- Heroku

## Testar a API

Para subir os containers Docker utilizando o orquestrador, basta executar os seguintes comandos:

```
mvn clean install -DskipTests=true  
docker-compose up -d 
```
*pulando os testes pois a conexão com o banco ainda não pode ser estabelecida

A API está com o swagger configurado, basta acessar pelo navegador o endereço: *http://localhost:8080/api/swagger-ui/*.

A API também está disponível no Heroku, podendo ser acessada pela URL: *https://ancient-beach-79989.herokuapp.com/api/swagger-ui/*

## Processo criativo

Visando facilitar o processo de criação e publicação da API, optei por utilizar a ferramenta Spring Boot, provendo a configuração de teste, aplicação REST, JPA, entre outros.
O banco escolhido para a persistencia dos dados foi o Postgres, por questão de familiridade.

Para os testes foi utilizada a biblioteca JUnit com Mockito, para objetos simulados (mock).

Foi utilizada também a ferramenta Swagger, a fim de prover uma documentação sucinta e um meio para se testar os endpoints da aplicação sem dependência de um software terceiro.
A aplicação e o banco de dados foram empacotados em dois containers distintos, mantendo seus ambientes independentes. A orquestração destes foi realizada por meio de Docker Compose.
