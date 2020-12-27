package com.backbase.kalah.services.impl;

import com.backbase.kalah.config.GameProperties;
import com.backbase.kalah.exceptions.GameNotFoundException;
import com.backbase.kalah.model.Game;
import com.backbase.kalah.repositories.GameRepository;
import com.backbase.kalah.services.GameService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameProperties gameProperties;
    private final GameRepository gameRepository;

    public Game createNewGame() {
        return gameRepository.createNewGame(gameProperties.getStones());
    }

    @Nonnull
    public Game getById(long gameId) {
        val game = gameRepository.getById(gameId);
        if (game == null) {
            throw new GameNotFoundException(gameId);
        }
        return game;
    }

    @Override
    public Game makeMove(long gameId, int pitId) {
        val game = getById(gameId);
        game.makeMove(pitId);
        gameRepository.save(game);
        return game;
    }
}
