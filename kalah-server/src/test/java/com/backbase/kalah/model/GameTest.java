package com.backbase.kalah.model;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static com.backbase.kalah.model.Game.modulusPitId;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {

    @Test
    void shouldInitDesk() {
        Game game = new Game(6);

        val pits = new TreeMap<Integer, Integer>();
        pits.put(1, 6);
        pits.put(2, 6);
        pits.put(3, 6);
        pits.put(4, 6);
        pits.put(5, 6);
        pits.put(6, 6);
        pits.put(7, 0);
        pits.put(8, 6);
        pits.put(9, 6);
        pits.put(10, 6);
        pits.put(11, 6);
        pits.put(12, 6);
        pits.put(13, 6);
        pits.put(14, 0);
        assertEquals(pits, game.getPits());
    }

    @Test
    void modulusShouldSupportOverflow() {
        assertEquals(1, modulusPitId(1));
        assertEquals(7, modulusPitId(7));
        assertEquals(14, modulusPitId(14));
        assertEquals(1, modulusPitId(15));
        assertEquals(2, modulusPitId(30));
    }

}
