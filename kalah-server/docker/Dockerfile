FROM openjdk:8-jdk-stretch

ENV SERVICE_HOME=/opt/kalah-server \
    LANG=C.UTF-8

WORKDIR $SERVICE_HOME

# can be overriden by container env
VOLUME $SERVICE_HOME/local_conf

COPY docker/run-service.sh bin/
COPY build/libs/kalah-server.jar $SERVICE_HOME/lib/
COPY conf conf

RUN useradd -m kalah-server && \
    chmod +x $SERVICE_HOME/bin/*.sh

CMD ["bin/run-service.sh"]

# local.server.port
EXPOSE 8080
# management.server.port
EXPOSE 8088
