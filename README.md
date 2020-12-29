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
To build the project (with tests execution):
```shell
./gradlew clean build
```

### Run the project locally
```shell
./gradlew clean bootRun -Dspring.profiles.active=h2
```
and open in browser [Local Home](http://localhost:8080/) for Swagger Doc or [Local actuator](http://localhost:8088/manage/) for actuator.

### Docker management
See [kalah-server/readme.md](kalah-server/readme.md)

### CI/CD
CI/CD pipeline can be easily set up using commands described in the readme.md

### TODOs
All of this is not explicitly required, but may be useful in real life project. We can discuss it.
- Implement `kalah-cli` - command-line interactive interface to play in the terminal
- Make two-client kalah. E.g. add a field with current player to response. Add back notifications (like webhooks), or reactive long polling subscriptions for signaling I/O (multiplayer).
- Aggregated JaCoCo coverage report
- Log each move into a separate sql table (column `version` may be used as a pair for game_id as unique key)


