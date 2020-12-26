package com.backbase.kalah.dto;

import lombok.Data;

import java.net.URI;
import java.util.Map;

@Data
public class GameDto {

    private long id;
    private URI uri;
    private Map<Integer, Integer> status;
}
