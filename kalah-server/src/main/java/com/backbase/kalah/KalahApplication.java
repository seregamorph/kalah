package com.backbase.kalah;

import com.backbase.kalah.config.GameProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(GameProperties.class)
public class KalahApplication {

    public static void main(String[] args) {
        SpringApplication.run(KalahApplication.class, args);
    }

}
