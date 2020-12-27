package com.backbase.kalah.exceptions;

public class GameNotFoundException extends RuntimeException {

    public GameNotFoundException(long gameId) {
        super("Game " + gameId + " not found");
    }
}
