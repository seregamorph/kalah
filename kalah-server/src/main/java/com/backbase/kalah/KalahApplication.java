package com.backbase.kalah;

import com.backbase.kalah.config.GameProperties;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableConfigurationProperties(GameProperties.class)
@Slf4j
public class KalahApplication {

    public static void main(String[] args) {
        SpringApplication.run(KalahApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady(ApplicationReadyEvent event) {
        Integer port = event.getApplicationContext().getEnvironment()
                .getProperty("local.server.port", Integer.class);
        // not defined in test scope
        if (port != null) {
            val url = String.format("http://localhost:%d/", port);
            log.info("Service started on {}", url);
        }
    }
}
