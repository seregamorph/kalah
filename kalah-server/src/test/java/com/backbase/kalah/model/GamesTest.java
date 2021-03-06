package com.backbase.kalah.model;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GamesTest {

    @Test
    void validateScenario1() {
        val game = new Game(6);

        game.makeMove(2);
        assertEquals(Arrays.asList(6, 0, 7, 7, 7, 7, 1, 7, 6, 6, 6, 6, 6, 0), game.getPits().asList());

        assertThrows(IllegalArgumentException.class, () -> game.makeMove(1));
        assertThrows(IllegalArgumentException.class, () -> game.makeMove(2));
        assertThrows(IllegalArgumentException.class, () -> game.makeMove(3));
        assertThrows(IllegalArgumentException.class, () -> game.makeMove(14));
    }

    @Test
    void validateScenario2() {
        val game = new Game(6);

        game.makeMove(8);
        assertEquals(Arrays.asList(6, 6, 6, 6, 6, 6, 0, 0, 7, 7, 7, 7, 7, 1), game.getPits().asList());
        game.makeMove(11);
        assertEquals(Arrays.asList(7, 7, 7, 7, 6, 6, 0, 0, 7, 7, 0, 8, 8, 2), game.getPits().asList());
        game.makeMove(4);
        assertEquals(Arrays.asList(7, 7, 7, 0, 7, 7, 1, 1, 8, 8, 1, 8, 8, 2), game.getPits().asList());
        game.makeMove(9);
        assertEquals(Arrays.asList(8, 8, 8, 0, 7, 7, 1, 1, 0, 9, 2, 9, 9, 3), game.getPits().asList());
        game.makeMove(5);
        assertEquals(Arrays.asList(8, 8, 8, 0, 0, 8, 2, 2, 1, 10, 3, 10, 9, 3), game.getPits().asList());
        game.makeMove(12);
        assertEquals(Arrays.asList(9, 9, 9, 1, 1, 9, 2, 3, 2, 10, 3, 0, 10, 4), game.getPits().asList());
        game.makeMove(1);
        assertEquals(Arrays.asList(0, 10, 10, 2, 2, 10, 3, 4, 3, 11, 3, 0, 10, 4), game.getPits().asList());
        game.makeMove(11);
        game.makeMove(12);
        game.makeMove(5);
        game.makeMove(2);
        assertEquals(Arrays.asList(0, 0, 11, 3, 1, 12, 5, 5, 4, 12, 1, 1, 12, 5), game.getPits().asList());
        game.makeMove(13);
        assertEquals(Arrays.asList(1, 1, 12, 4, 2, 13, 5, 6, 5, 13, 2, 2, 0, 6), game.getPits().asList());
        game.makeMove(5);
        game.makeMove(6);
        assertEquals(Arrays.asList(2, 2, 13, 5, 1, 1, 8, 7, 6, 14, 3, 3, 1, 6), game.getPits().asList());
        game.makeMove(6);
        assertEquals(Arrays.asList(2, 2, 13, 5, 1, 0, 9, 7, 6, 14, 3, 3, 1, 6), game.getPits().asList());
        game.makeMove(5);
        assertEquals(Arrays.asList(2, 2, 13, 5, 0, 0, 17, 0, 6, 14, 3, 3, 1, 6), game.getPits().asList());

        game.makeMove(13);
        game.makeMove(11);
        game.makeMove(13);
        game.makeMove(10);
        game.makeMove(6);
        game.makeMove(5);
        game.makeMove(13);
        game.makeMove(11);
        game.makeMove(2);
        game.makeMove(10);
        game.makeMove(4);
        game.makeMove(8);
        game.makeMove(6);
        game.makeMove(5);
        game.makeMove(9);
        game.makeMove(6);
        assertEquals(Arrays.asList(0, 0, 0, 0, 0, 0, 31, 0, 0, 0, 0, 0, 0, 41), game.getPits().asList());

    }
}
