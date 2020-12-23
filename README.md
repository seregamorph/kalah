# Analysis
## Technical decisions
### Server
- Java 8 plus Lombok
- gradle
- Spring Boot (Spring Initializr)
- ✅ SpringDoc for swagger doc
- ❌ no spring-security (task does not explicitly require it)
- ❌ no async I/O is required here, so by KISS principle we should not use it
- ❌ spring-hateoas is not suitable here because it has another format of references

### Client
The task does not require, but probably it's assumed that there should be a client for API.

## Persistence
TODO


