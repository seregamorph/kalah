# Kalah
### Server
- Java 8 plus Lombok
- gradle
- Spring Boot
- ✅ SpringDoc for swagger doc (default URL)
- ❌ no spring-security (task does not explicitly require authorization)
- ❌ no async I/O is required here, so by KISS principle we should not use it
- ❌ spring-hateoas is not suitable here because it has another format of references (self uri)

### Client
The task does not require, but probably it's assumed that there should be a client for API.

### Persistence
JdbcTemplate is used. It allows to implement compare-and-set (versioned) record update. In case of parallel requests all inconsistent saves will be rejected.

### Logging
Default console output logging is set up. It's a microservice, so it's a responsibility of container manager to handle it. Servlet IO is printed.

### Build
To build the project:
```shell
./gradlew clean build
```

### Docker management
See [kalah-server/readme.md](kalah-server/readme.md)

### Run the project locally
```shell
./gradlew clean bootRun -Dspring.profiles.active=h2
```
and open in browser [Local Home](http://localhost:8080/) for Swagger Doc or [Local actuator](http://localhost:8088/manage/) for actuator.
