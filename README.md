# Honoraires Batch

Partie Batch de la plateforme intégrée pour la gestion des prestations et honoraires des intermédiaires de justice  

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
3. Exécuter avec `./mvnw spring-boot:run`
