# Consumer com Spring e Kafka

Aplicação **Spring Boot** que consome mensagens de um tópico no **Kafka**, processa as informações recebidas e armazena os dados em um banco de dados **Oracle**.

## Pré-requisitos

- **JDK 17**
- **Docker** instalado

## Funcionalidades

- Consumo de mensagens de um tópico Kafka;
- Validação dos dados recebidos;
- Armazenamento das informações validadas no banco de dados Oracle.

## Como executar o projeto utilizando Docker

1. Na pasta raiz do projeto, execute o seguinte comando para construir o projeto:

   ```bash
   ./gradlew clean build

2. Após a construção, inicie os containers com o comando:
    ```bash
    docker-compose up --build

3. A aplicação estará disponível em:

http://localhost:8080/swagger-ui/index.html

## Como inserir uma mensagem no tópico Kafka

1. Identifique o ID ou nome do container Kafka rodando o comando:
   ```bash
   docker ps

2. Substitua <nome_do_container_kafka> pelo nome ou ID do seu container e entre no terminal do container Kafka:
   ```bash
   docker exec -it <nome_do_container_kafka> bash

3. Dentro do container, execute o seguinte comando para inserir uma mensagem no tópico topic-1:
   ```bash
   kafka-console-producer.sh --topic topic-1 --bootstrap-server localhost:9092

4. Agora, adicione o JSON da mensagem que deseja inserir. Exemplo de formato da mensagem:
   ```bash
   {"nome": "Felizardo da Silva", "cpf": "94912738093", "dataNascimento": "2000-01-01"}