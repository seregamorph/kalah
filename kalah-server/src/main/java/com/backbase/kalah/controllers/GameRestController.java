package com.backbase.kalah.controllers;

import com.backbase.kalah.api.ApiGames;
import com.backbase.kalah.dto.GameDto;
import com.backbase.kalah.exceptions.ErrorResponse;
import com.backbase.kalah.model.Game;
import com.backbase.kalah.services.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@RequestMapping(ApiGames.ENDPOINT_GAMES)
@RequiredArgsConstructor
public class GameRestController extends AbstractRestController implements ApiGames {

    private final GameService gameService;

    @Operation(summary = "Create a game, returns created game.")
    @PostMapping
    @ResponseStatus(CREATED)
    public GameDto create() {
        val game = gameService.createNewGame();
        return map(game);
    }

    @Operation(summary = "Get game by id", responses = {
            @ApiResponse(responseCode = "200", description = "Game found by id"),
            @ApiResponse(responseCode = "404", description = "Game not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping(ENDPOINT_GAME_ID)
    public GameDto getById(@PathVariable(PARAM_GAME_ID) long id) {
        val game = gameService.getById(id);
        return map(game);
    }

    @Operation(summary = "Make a move, returns current status", responses = {
            @ApiResponse(responseCode = "200", description = "Move was made"),
            @ApiResponse(responseCode = "400", description = "The move is forbidden",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Game not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
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
