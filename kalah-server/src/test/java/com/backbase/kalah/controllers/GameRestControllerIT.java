package com.backbase.kalah.controllers;

import org.junit.jupiter.api.Test;

import static com.backbase.kalah.controllers.GameRestController.ENDPOINT;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesRegex;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GameRestControllerIT extends AbstractRestControllerMockMvcIT {

    @Test
    void createGameShouldInitDesk() throws Exception {
        mockMvc.perform(post(ENDPOINT))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.uri", matchesRegex("http://localhost/games/(\\d+)")))
                .andExpect(jsonPath("$.status.1", equalTo(6)))
                .andExpect(jsonPath("$.status.2", equalTo(6)))
                .andExpect(jsonPath("$.status.3", equalTo(6)))
                .andExpect(jsonPath("$.status.4", equalTo(6)))
                .andExpect(jsonPath("$.status.5", equalTo(6)))
                .andExpect(jsonPath("$.status.6", equalTo(6)))
                .andExpect(jsonPath("$.status.7", equalTo(0)))
                .andExpect(jsonPath("$.status.8", equalTo(6)))
                .andExpect(jsonPath("$.status.9", equalTo(6)))
                .andExpect(jsonPath("$.status.10", equalTo(6)))
                .andExpect(jsonPath("$.status.11", equalTo(6)))
                .andExpect(jsonPath("$.status.12", equalTo(6)))
                .andExpect(jsonPath("$.status.13", equalTo(6)))
                .andExpect(jsonPath("$.status.14", equalTo(0)));
    }

}
