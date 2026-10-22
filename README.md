# spring-boot-gcp-spanner

[![Java CI with Maven](https://github.com/hendisantika/spring-boot-gcp-spanner/actions/workflows/maven.yml/badge.svg)](https://github.com/hendisantika/spring-boot-gcp-spanner/actions/workflows/maven.yml)
![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.1-6DB33F?logo=springboot)
![Spring Cloud GCP](https://img.shields.io/badge/Spring%20Cloud%20GCP-8.2.4-4285F4?logo=googlecloud)

A simple REST API for `User` records, built with Spring Boot and stored in
[Google Cloud Spanner](https://cloud.google.com/spanner) via Spring Data Spanner.

## Tech Stack

- Java 25
- Spring Boot 4.1.1 (Web, DevTools, Configuration Processor)
- Spring Cloud GCP 8.2.4 (`spring-cloud-gcp-starter-data-spanner`)
- Spring Boot Gson (Spanner's mapping context needs a `Gson` bean)
- Lombok
- Maven (wrapper included)

## Prerequisites

- JDK 25
- A GCP project with a Spanner instance and database
- A service account key (JSON) that can access Spanner

## Spanner Schema

Create the `users` table in your Spanner database:

```sql
CREATE TABLE users (
  user_id STRING(36) NOT NULL,
  name    STRING(MAX),
  email   STRING(MAX),
  country STRING(MAX),
) PRIMARY KEY (user_id);
```

## Configuration

Update `src/main/resources/application.properties` with your values:

```properties
spring.application.name=spring-boot-gcp-spanner
spring.cloud.gcp.spanner.project-id=<your-project-id>
spring.cloud.gcp.credentials.location=classpath:<your-service-account-key>.json
spring.cloud.gcp.spanner.instance-id=<your-instance-id>
spring.cloud.gcp.spanner.database=<your-database>
```

Put the service account key in `src/main/resources/`. JSON files in that folder are
listed in `.gitignore`, so **the key is never committed**. You can also use
`file:/absolute/path/key.json`, or leave `credentials.location` out and rely on
[Application Default Credentials](https://cloud.google.com/docs/authentication/application-default-credentials)
(`gcloud auth application-default login`).

## Build & Run

```bash
./mvnw clean package   # build and run tests
./mvnw spring-boot:run # start the app on http://localhost:8080
```

The tests don't need GCP credentials or a running Spanner instance. They use
test-only properties (`src/test/resources/application.properties`), `NoCredentials`
and mocked Spanner clients, which is what lets CI pass without secrets.

## REST API

Base path: `/api/v1`

| Method   | Endpoint                       | Description                |
|----------|--------------------------------|----------------------------|
| `GET`    | `/users`                       | List all users             |
| `GET`    | `/users/{id}`                  | Get a user by id           |
| `GET`    | `/users/name/{name}`           | Find users by name         |
| `GET`    | `/users/country/{country}`     | Find users by country      |
| `POST`   | `/users`                       | Create a user (random UUID)|
| `PUT`    | `/users/{id}`                  | Update a user              |
| `DELETE` | `/users/{id}`                  | Delete a user              |

Example:

```bash
curl -X POST http://localhost:8080/api/v1/users \
  -H "Content-Type: application/json" \
  -d '{"name":"Hendi","email":"hendi@example.com","country":"Indonesia"}'
```

## CI/CD & Dependency Updates

- **GitHub Actions** (`.github/workflows/maven.yml`): builds and tests with Temurin
  JDK 25 on every push and pull request to `main`, then submits the dependency graph.
- **Dependabot** (`.github/dependabot.yml`):
  - Maven: checked daily at 05:00 Asia/Jakarta, with updates grouped into Spring Boot,
    Spring Cloud, Google Cloud, Maven plugins, and other minor/patch bumps
  - GitHub Actions: checked weekly and batched into one PR
  - Conventional commit prefixes (`build(deps)`, `ci(deps)`) and labels on every PR

## Author

**Hendi Santika**

- Link: [s.id/hendisantika](https://s.id/hendisantika)
- Telegram: [@hendisantika34](https://t.me/hendisantika34)
