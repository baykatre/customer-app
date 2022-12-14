# Customer App

Is a basic Spring Boot authentication app with JWT Token.

### Prerequisites

- Docker

Make sure export jar via maven, before run docker-compose file.

```
mvn clean install -DskipTests
```

### Run

```
docker compose up -d
```

### Document

```
http://localhost:8080/swagger-ui/index.html
```
![main-screen](https://raw.githubusercontent.com/baykatre/customer-app/main/photos/swagger.png "Main")

## Authors

- **Anıl Öztürk** - _Software Developer_ - [GitHub](https://github.com/baykatre)

## Thanks

[Spring Boot Security + JWT Tutorial](https://www.javainuse.com/spring/boot-jwt) by [javainuse](https://www.javainuse.com)

[Using OpenAPI 3.0](https://www.baeldung.com/spring-rest-openapi-documentation) by [baeldung](https://baeldung.com)
