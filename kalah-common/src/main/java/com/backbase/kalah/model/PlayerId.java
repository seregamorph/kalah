package com.backbase.kalah.model;

import lombok.Getter;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public enum PlayerId {
    FIRST(1),
    SECOND(2);

    @Getter
    private final int num;
    /**
     * Inclusive (1 or 8)
     */
    private final int beginPitId;
    /**
     * Kalah pitId
     * Inclusive (7 or 14)
     */
    private final int endPitId;

    PlayerId(int num) {
        this.num = num;
        beginPitId = (num - 1) * 7 + 1;
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
                        String.format("Wrong pitId %d, should be in interval [1..6] or [8..13]", pitId)));
    }

    public static PlayerId fromNum(int num) {
        if (num == 0) {
            return null;
        }
        return Stream.of(values())
                .filter(playerId -> playerId.getNum() == num)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Illegal player num " + num));
    }
}
