package com.backbase.kalah.controllers;

import com.backbase.kalah.dto.GameDto;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;

import static com.backbase.kalah.controllers.GameRestController.ENDPOINT;
import static com.backbase.kalah.controllers.GameRestController.ENDPOINT_GAME_ID;
import static com.backbase.kalah.controllers.GameRestController.ENDPOINT_MOVE;
import static com.backbase.kalah.test.ResultMatchers.collect;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesRegex;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GameRestControllerIT extends AbstractRestControllerMockMvcIT {

    @Test
    void createGameShouldInitDeskAndGetByUri() throws Exception {
        val game = createAndValidateGame();

        mockMvc.perform(get(game.getUri()))
                .andExpect(status().isOk())
                .andExpect(gameMatchers(6, 6, 6, 6, 6, 6, 0,
                        6, 6, 6, 6, 6, 6, 0));
    }

    @Test
    void getGameByIdShouldReturn404() throws Exception {
        mockMvc.perform(get(ENDPOINT + ENDPOINT_GAME_ID, 1))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Game 1 not found"));
    }

    @Test
    void shouldMakeAllowedMove() throws Exception {
        val game = createAndValidateGame();

        mockMvc.perform(put(ENDPOINT + ENDPOINT_MOVE, game.getId(), 1))
                .andExpect(status().isOk())
                .andExpect(gameMatchers(0, 7, 7, 7, 7, 7, 1,
                        6, 6, 6, 6, 6, 6, 0));
    }

    @Test
    void shouldNotMakeNotAllowedMove() throws Exception {
        val game = createAndValidateGame();

        mockMvc.perform(put(ENDPOINT + ENDPOINT_MOVE, game.getId(), 7))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Wrong pitId 7, should be in interval [1..6] or [8..13]"));

    }

    private GameDto createAndValidateGame() throws Exception {
        val response = mockMvc.perform(post(ENDPOINT))
                .andExpect(status().isCreated())
                .andExpect(gameMatchers(6, 6, 6, 6, 6, 6, 0,
                        6, 6, 6, 6, 6, 6, 0))
                .andReturn().getResponse().getContentAsString();

        return readJson(response, GameDto.class);
    }

    private static ResultMatcher gameMatchers(int... pits) {
        val matchers = new ArrayList<ResultMatcher>();
        matchers.add(jsonPath("$.id").isNumber());
        matchers.add(jsonPath("$.uri", matchesRegex("http://localhost/games/(\\d+)")));
        for (int i = 1; i <= 14; i++) {
            matchers.add(jsonPath("$.status." + i, equalTo(pits[i - 1])));
        }
        return collect(matchers);
    }

}
