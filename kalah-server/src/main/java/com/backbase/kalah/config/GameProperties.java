package com.backbase.kalah.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "game")
@Getter
@Setter
public class GameProperties {

    private static final int DEFAULT_STONES = 6;

    private int stones = DEFAULT_STONES;


}
