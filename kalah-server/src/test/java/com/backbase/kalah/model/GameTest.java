package com.backbase.kalah.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.backbase.kalah.model.Game.modulusPitId;
import static com.backbase.kalah.model.Game.oppositePitId;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {

    @Test
    void shouldInitDesk() {
        Game game = new Game(6);

        assertEquals(Arrays.asList(6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0), game.getPits().asList());
    }

    @Test
    void modulusShouldSupportOverflow() {
        assertEquals(1, modulusPitId(1));
        assertEquals(7, modulusPitId(7));
        assertEquals(14, modulusPitId(14));
        assertEquals(1, modulusPitId(15));
        assertEquals(2, modulusPitId(30));
    }

    @Test
    void oppositePitIdShouldBeEvaluated() {
        assertEquals(1, oppositePitId(13));
        assertEquals(6, oppositePitId(8));
        assertEquals(13, oppositePitId(1));
        assertEquals(8, oppositePitId(6));
    }

}
