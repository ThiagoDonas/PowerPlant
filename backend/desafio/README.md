# PowerPlant API

## 📌 Sobre o Projeto
Este projeto é uma API desenvolvida em **Kotlin** com **Spring Boot** que gerencia e disponibiliza informações sobre usinas de geração de energia elétrica. Ele permite:

- Fazer o **download** e processamento de um arquivo CSV com os dados das usinas.
- Armazenar e atualizar os dados no banco de dados **PostgreSQL**.
- Expor endpoints **RESTful** para consulta dos dados, incluindo a listagem das **5 maiores usinas** por capacidade instalada.
- Agendar a atualização automática dos dados **duas vezes ao dia** e permitir a atualização manual via endpoint.

## 🚀 Como Rodar o Projeto com Docker
O projeto está configurado para rodar usando **Docker** e **Docker Compose**. Para iniciar a aplicação siga os passos abaixo:

### **1️⃣ Instale as dependências necessárias**
Antes de começar, garanta que você tem o **Docker** e **Docker Compose** instalados:
- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)

### **2️⃣ Iniciar os containers do projeto**
Para rodar a aplicação e o banco de dados, execute:
```sh
docker-compose up -d
```
Isso irá:
- Criar um container para o banco de dados **PostgreSQL**.
- Criar um container para a aplicação **Spring Boot**.
- Iniciar a API na porta **8080**.

### **4️⃣ Verificar se os containers estão rodando**
Para conferir os containers em execução, use:
```sh
docker ps
```

### **5️⃣ Acessar a API**
Após o projeto estar rodando, os endpoints estarão disponíveis em:
- **Listar todas as usinas:** `GET http://localhost:8080/power_plants`
- **Listar as 5 maiores usinas:** `GET http://localhost:8080/power_plants/top5`
- **Baixar e processar manualmente o CSV:** `POST http://localhost:8080/power_plants/download`

## 🔧 Como Rodar o Projeto Sem Docker
Se preferir rodar o projeto localmente sem Docker, siga os passos abaixo:

### **1️⃣ Instale as dependências**
Certifique-se de ter instalado:
- **JDK 21**
- **Gradle**
- **PostgreSQL**

### **2️⃣ Configurar o banco de dados**
Crie um banco de dados chamado `powerplant_db` e atualize as credenciais no `application.yml`:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/powerplant_db
    username: admin
    password: secret
```

### **3️⃣ Rodar a aplicação**
Com o banco configurado, execute o seguinte comando na raiz do projeto:
```sh
./gradlew bootRun
```
Isso iniciará a API localmente na porta `8080`.

## 📖 Documentação da API
A API conta com uma documentação interativa via **Swagger**, disponível em:
```
http://localhost:8080/swagger-ui/index.html
```

## 📌 Agendamento de Atualização
A API possui um **job agendado** para baixar e processar o CSV automaticamente **duas vezes ao dia**:
- **08:00 da manhã**
- **20:00 da noite**

Caso precise atualizar os dados manualmente, basta chamar o endpoint:
```sh
POST http://localhost:8080/power_plants/download
```

## ✅ Tecnologias Utilizadas
- **Kotlin**
- **Spring Boot**
- **PostgreSQL**
- **Flyway** (Migração de banco de dados)
- **Docker & Docker Compose**
- **OpenCSV** (Leitura e processamento do CSV)
- **Swagger** (Documentação da API)




