package com.backbase.kalah.controllers;

import com.backbase.kalah.dto.GameDto;
import com.backbase.kalah.model.Game;
import com.backbase.kalah.services.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@Tag(name = "Game")
@RestController
@RequestMapping(GameRestController.ENDPOINT)
@RequiredArgsConstructor
public class GameRestController extends AbstractRestController {

    static final String ENDPOINT = "/games";

    private static final String PARAM_GAME_ID = "gameId";
    private static final String PARAM_PIT_ID = "pitId";
    public static final String ENDPOINT_GAME_ID = "/{" + PARAM_GAME_ID + "}";
    private static final String ENDPOINT_MOVE = ENDPOINT_GAME_ID + "/pits/{" + PARAM_PIT_ID + "}";

    private final GameService gameService;

    @Operation(summary = "Create a game")
    @PostMapping
    @ResponseStatus(CREATED)
    public GameDto create() {
        val game = gameService.createNewGame();
        return map(game);
    }

    @Operation(summary = "Get game by id")
    @GetMapping(ENDPOINT_GAME_ID)
    public GameDto getById(@PathVariable(PARAM_GAME_ID) long id) {
        val game = gameService.getById(id);
        return map(game);
    }

    @Operation(summary = "Make a move")
    @PutMapping(ENDPOINT_MOVE)
    public GameDto move(@PathVariable(PARAM_GAME_ID) long gameId,
                        @PathVariable(PARAM_PIT_ID) int pitId) {
        val game = gameService.makeMove(gameId, pitId);
        return map(game);
    }

    private GameDto map(Game game) {
        val uri = location(ENDPOINT_GAME_ID, game.getId());
        return new GameDto()
                .setId(game.getId())
                .setUri(uri)
                .setStatus(game.getPits().asMap());
    }
}
