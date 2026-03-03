# Honoraires Batch

Application Spring Batch pour le calcul automatique des honoraires des intermédiaires (cabinets de recouvrement et avocats).

## Fonctionnalités
- Lecture de fichiers CSV contenant les dossiers
- Sauvegarde des résultats dans PostgreSQL

## Technologies
- Java 17
- Spring Boot 3.1.5
- Spring Batch
- PostgreSQL
- Maven
- Lombok

## Configuration
1. Créer une base PostgreSQL nommée `recouvrement_batch` sur le port 5433
2. Modifier `application.properties` si nécessaire
3. Exécuter avec `./mvnw spring-boot:run`
