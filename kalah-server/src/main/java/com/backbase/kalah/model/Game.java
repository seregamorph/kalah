package com.backbase.kalah.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.val;

import java.util.stream.Stream;

/**
 * Game desk state.
 */
@RequiredArgsConstructor
public class Game {

    /**
     * key: 1-based pitId, value: count of stones in the pit
     */
    @Getter
    private final Pits pits;

    @Getter
    @Setter
    private Long id;

    /**
     * Current player. Initially null, set on first turn (either First or Second player).
     */
    @Setter
    private PlayerId currentPlayer;

    /**
     * Serialization version to avoid data race
     */
    @Getter
    @Setter
    private int version;

    /**
     * Init desk.
     *
     * @param stones number of stones (usually 3, 4 or 6).
     */
    public Game(int stones) {
        this(new Pits(stones));
    }

    public void makeMove(int pitId) {
        val pitPlayer = PlayerId.getPlayerId(pitId);
        val otherPitPlayer = pitPlayer.otherPlayerId();
        if (currentPlayer == null) {
            // first move
            currentPlayer = pitPlayer;
        } else if (currentPlayer != pitPlayer) {
            throw new IllegalArgumentException("It's " + currentPlayer + " move");
        }
        int stones = pits.get(pitId);
        if (stones == 0) {
            throw new IllegalArgumentException("Pit " + pitId + " has 0 stones");
        }
        pits.put(pitId, 0);
        int pos = pitId;
        while (stones > 0) {
            pos++;
            int putPitId = modulusPitId(pos);
            if (otherPitPlayer.kalahPitId() == putPitId) {
                // it is other player's kalah, skip it
                continue;
            }
            stones--;
            int putPitStones = pits.get(putPitId);
            pits.put(putPitId, putPitStones + 1);
            // it was last stone
            if (stones == 0) {
                if (putPitId == pitPlayer.kalahPitId()) {
                    // players kalah - one more turn
                    // not switching currentPlayer
                } else {
                    // your empty pit
                    if (pitPlayer.includesPitId(putPitId) && putPitStones == 0) {
                        int oppositePitId = oppositePitId(putPitId);
                        int oppositeStones = pits.get(oppositePitId);
                        if (oppositeStones > 0) {
                            // take your and opposite
                            pits.put(pitPlayer.kalahPitId(), pits.get(pitPlayer.kalahPitId())
                                    + 1 + oppositeStones);
                            pits.put(putPitId, 0);
                            pits.put(oppositePitId, 0);
                        }
                    }
                    currentPlayer = currentPlayer.otherPlayerId();
                }
                handleGameOver();
            }
        }
    }

    private void handleGameOver() {
        boolean gameIsOver = Stream.of(PlayerId.values())
                .anyMatch(playerId -> playerId.pitIds()
                        .map(pits::get)
                        .allMatch(Integer.valueOf(0)::equals));

        if (gameIsOver) {
            for (val playerId : PlayerId.values()) {
                playerId.pitIds().forEach(pitId -> {
                    pits.put(playerId.kalahPitId(), pits.get(playerId.kalahPitId())
                            + pits.get(pitId));
                    pits.put(pitId, 0);
                });
            }
        }
    }

    static int oppositePitId(int pitId) {
        assert pitId % 7 != 0;
        return 14 - pitId;
    }

    static int modulusPitId(int pitId) {
        return ((pitId - 1) % 14) + 1;
    }

}
