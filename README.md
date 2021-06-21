José Eduardo Alonso -> 
<https://www.linkedin.com/in/alonso-je/>

# BACK-END

## Sobre o projeto

API REST  para prover os recursos solicitados pelo cliente, em um fluxo de cadastro de ordens de serviços, regras de negócio, configurações e demais necessidades.

## Tecnologias utilizadas
- Java 11
- Spring Boot
- JPA / Hibernate
- Maven
- REST
- Jasper
- Lombok
- Docker ou Mysql

## Pré-requisitos:
- Java 11
- Lombok
- Docker ou Mysql

```bash
# clonar repositório
git clone https://github.com/AlonsoJE/avaliacao-back-end.git

## importar o projeto atendimento em ambiente de desenvolvimento utilizando Maven

# antes de executar o projeto
  script em  resources\scripts\inserts.sql

# executar o arquivo docker-compose 
 script em /stack.yml
 
# caso prefira não utilizar o docker, basta criar um database atendimentodata e utilizar o mesmo script sql fornecido para popular as tabelas
create tadabase atendimentodata;
```

# FRONT-END

## Sobre o projeto

Um projeto Vue.js, se tratando de uma single page application que realiza o consumo e utilização dos recursos disponibilizados por uma API REST em java.

# Tecnologias utilizadas
- Node.js
- vue Cli
- Vue.js 2
- Vuetify
- Axios

## Pré-requisitos:
- Node.js
- VueCli

```bash
# clonar repositório
git clone https://github.com/AlonsoJE/avaliacao-front-end.git

# navegar ao a raiz do projeto /atendimento

# instalar o VueCli globalmente
npm install -g @vue/cli

# verificar dependencias e bibliotecas presentes em /dist que não são versionadas
npm i

# dentro da raiz do projeto /atendimento para executar o servidor 
npm run serve

# build 
npm run build
 
# executar em electron
npm run electron:serve

# build em electron
npm run electron:build
```
