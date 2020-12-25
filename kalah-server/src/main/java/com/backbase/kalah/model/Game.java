package com.backbase.kalah.model;

import lombok.val;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Game desk state.
 */
public class Game {

    /**
     * key: 1-based pitId, value: count of stones in the pit
     */
    private final SortedMap<Integer, Integer> pits;

    /**
     * Current player. Initially null, set on first turn (either First or Second player).
     */
    private PlayerId currentPlayer;

    private boolean complete;

    /**
     * Init desk.
     *
     * @param stones number of stones (usually 3, 4 or 6).
     */
    public Game(int stones) {
        pits = new TreeMap<>();
        for (PlayerId playerId : PlayerId.values()) {
            pits.put(playerId.kalahPitId(), 0);
            playerId.pitIds()
                    .forEach(pitId -> pits.put(pitId, stones));
        }
    }

    public SortedMap<Integer, Integer> getPits() {
        return new TreeMap<>(pits);
    }

    public void makeMove(int pitId) {
        if (complete) {
            throw new IllegalArgumentException("Game is complete");
        }
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
        int pos = pitId + 1;
        while (stones > 0) {
            int putPitId = modulusPitId(pos);
            if (otherPitPlayer.kalahPitId() == putPitId) {
                // it is other player's kalah, skip it
                continue;
            }
            stones--;
            int pitStones = pits.get(putPitId);
            pits.put(putPitId, pitStones + 1);
            // last move
            if (stones == 0) {
                if (pitPlayer.pitIds()
                        .map(pits::get)
                        .allMatch(Integer.valueOf(0)::equals)) {
                    // game is over
                    complete = true;

                }
            }
        }
    }

    static int modulusPitId(int pitId) {
        return ((pitId - 1) % 14) + 1;
    }

}
