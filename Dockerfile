# Etapa 1: Construção
FROM maven:3.9.6-amazoncorretto-21 AS build
WORKDIR /app
# Copiar apenas o arquivo pom.xml para baixar as dependências
COPY pom.xml .
RUN mvn dependency:go-offline
# Copiar o código-fonte e construir o projeto
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Execução
FROM amazoncorretto:21
WORKDIR /app
COPY --from=build app/target/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
