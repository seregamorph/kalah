package com.backbase.kalah.services;

import com.backbase.kalah.model.Game;

import javax.annotation.Nonnull;

public interface GameService {

    Game createNewGame();

    @Nonnull
    Game getById(long gameId);

    Game makeMove(long gameId, int pitId);

}
