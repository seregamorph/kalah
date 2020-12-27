
### Build image
```shell
./gradlew clean build
cd kalah-server
docker build --pull -t kalah-server -f ./docker/Dockerfile .
```

### Run container (docker run)
```shell
docker run --rm -it -p 8080:8080 --name kalah-server kalah-server
```

### Run container (docker-compose)
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

