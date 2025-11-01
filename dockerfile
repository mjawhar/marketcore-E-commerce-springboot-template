# -------- Étape 1 : compilation avec Maven et repackage Spring Boot --------
FROM maven:3.8.6-eclipse-temurin-17-alpine AS build

WORKDIR /app

# 1) copie du pom et téléchargement des dépendances
COPY pom.xml .
RUN mvn dependency:go-offline -B

# 2) copie du code source
COPY src ./src

# 3) compilation + repackage Spring Boot (génère un 'fat jar' avec manifeste)
RUN mvn clean package spring-boot:repackage -DskipTests -B

# -------- Étape 2 : image de production --------
FROM eclipse-temurin:17-jdk AS runtime

WORKDIR /app

# on récupère le JAR “ré‑emballé”
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

# variables à surcharger au run-time si besoin
ENV SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/votre_database
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=changeme

ENTRYPOINT ["sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar app.jar"]
