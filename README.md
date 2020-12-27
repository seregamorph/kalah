# Analysis
## Technical decisions
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
