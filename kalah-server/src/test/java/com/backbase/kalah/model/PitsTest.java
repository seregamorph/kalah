package com.backbase.kalah.model;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PitsTest {

    @Test
    void shouldDecodeEncoded() {
        val pits = new Pits(6);
        val encoded = pits.encode();
        val restored = Pits.decode(encoded);

        assertEquals(pits.asList(), restored.asList());
    }

}
