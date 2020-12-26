package com.backbase.kalah.model;

import lombok.val;
import org.junit.jupiter.api.Test;

import static com.backbase.kalah.model.PitUtils.pits;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GamesTest {

    @Test
    void validateScenario() {
        val game = new Game(6);

        game.makeMove(2);

        assertEquals(pits(6, 0, 7, 7, 7, 7, 1, 7, 6, 6, 6, 6, 6, 0), game.getPits());
    }
}
