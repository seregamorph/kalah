package com.backbase.kalah.model;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public enum PlayerId {
    FIRST(0),
    SECOND(1);

    /**
     * Inclusive (1 or 8)
     */
    private final int beginPitId;
    /**
     * Kalah pitId
     * Inclusive (7 or 14)
     */
    private final int endPitId;

    PlayerId(int index) {
        beginPitId = index * 7 + 1;
        endPitId = beginPitId + 6;
    }

    public PlayerId otherPlayerId() {
        return this == FIRST ? SECOND : FIRST;
    }

    public boolean includesPitId(int pitId) {
        return pitId >= beginPitId && pitId < endPitId;
    }

    public IntStream pitIds() {
        return IntStream.range(beginPitId, endPitId);
    }

    public int kalahPitId() {
        return endPitId;
    }

    public static PlayerId getPlayerId(int pitId) {
        return Stream.of(PlayerId.values())
                .filter(playerId -> playerId.includesPitId(pitId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Wrong pitId %d, should be in interval [1..14]", pitId)));
    }
}
