#!/bin/bash

java -Dspring.config.additional-location=./conf/:./local_conf/ -jar lib/kalah-server.jar
