package com.backbase.kalah.controllers;

import com.backbase.kalah.client.KalahClient;
import com.backbase.kalah.exceptions.GameNotFoundException;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GameRestControllerWebIT extends AbstractRestControllerWebIT {

    private KalahClient client;

    @BeforeEach
    void initClient() {
        val rest = createRestTemplate();
        client = new KalahClient(rest, baseUri());
    }

    @Test
    void shouldCreateAndGetGame() {
        val game = client.createGame();
        assertThat(game.getId(), greaterThan(0L));

        val gotGame = client.getGameById(game.getId());
        assertEquals(game.getId(), gotGame.getId());
    }

    @Test
    void shouldNotGetNonExistingGame() {
        assertThrows(GameNotFoundException.class, () -> client.getGameById(-1));
    }

    @Test
    void shouldCreateAndMakeMove() {
        val game = client.createGame();
        assertThat(game.getId(), greaterThan(0L));

        val gotGame = client.makeMove(game.getId(), 1);
        // todo check status
        assertEquals(7, gotGame.getStatus().get(2));
    }

}
