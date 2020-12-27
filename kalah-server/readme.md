
### Build image
```shell
./gradlew clean build
cd kalah-server
docker build --pull -t kalah-server -f ./docker/Dockerfile .
```

### Run container
```shell
docker-compose -f docker/docker-compose.yaml up -d
```

### Read logs
```shell
docker logs kalah-server -f --tail 1000
```

### Shell enter
```shell
docker exec -it kalah-server bash
```

