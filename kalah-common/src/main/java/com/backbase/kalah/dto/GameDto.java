package com.backbase.kalah.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.net.URI;
import java.util.Map;

@Data
public class GameDto {

    @Schema(description = "Game id")
    private long id;
    @Schema(description = "Game self reference")
    private URI uri;
    @Schema(description = "Game status, keys are [\"1\"..\"14\"], values are stones count for each pit")
    private Map<Integer, Integer> status;
}
