package com.backbase.kalah.model;

import org.junit.jupiter.api.Test;

import static com.backbase.kalah.model.PlayerId.FIRST;
import static com.backbase.kalah.model.PlayerId.SECOND;
import static com.backbase.kalah.model.PlayerId.getPlayerId;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlayerIdTest {

    @Test
    void shouldBePlayerIdKalah() {
        assertEquals(7, FIRST.kalahPitId());
        assertEquals(14, SECOND.kalahPitId());
    }

    @Test
    void shouldGetPlayerIdWithoutKalah() {
        assertEquals(FIRST, getPlayerId(1));
        assertEquals(SECOND, getPlayerId(13));
    }

    @Test
    void getPlayerIdShouldThrowExceptionOnIllegalPitId() {
        assertThrows(IllegalArgumentException.class, () -> getPlayerId(0));
        assertThrows(IllegalArgumentException.class, () -> getPlayerId(15));
    }

}
