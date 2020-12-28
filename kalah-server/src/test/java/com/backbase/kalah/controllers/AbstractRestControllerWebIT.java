package com.backbase.kalah.controllers;

import com.backbase.kalah.interceptors.LoggingInterceptor;
import lombok.val;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class AbstractRestControllerWebIT {

    @LocalServerPort
    private int localPort;

    protected URI baseUri() {
        return URI.create("http://127.0.0.1:" + localPort);
    }

    protected RestTemplate createRestTemplate() {
        val rest = new RestTemplate();
        rest.setInterceptors(Collections.singletonList(new LoggingInterceptor()));
        return rest;
    }

}
