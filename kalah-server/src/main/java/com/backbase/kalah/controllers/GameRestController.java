package com.backbase.kalah.controllers;

import com.backbase.kalah.dto.GameDto;
import com.backbase.kalah.model.Game;
import com.backbase.kalah.services.GameService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.backbase.kalah.controllers.ControllerConstants.ENDPOINT_ID;
import static com.backbase.kalah.controllers.ControllerConstants.PARAM_ID;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(GameRestController.ENDPOINT)
@RequiredArgsConstructor
public class GameRestController extends AbstractRestController {

    static final String ENDPOINT = "/games";

    private static final String PARAM_PIT_ID = "pitId";
    private static final String ENDPOINT_MOVE = ENDPOINT_ID + "/pits/{" + PARAM_PIT_ID + "}";

    private final GameService gameService;

    @PostMapping
    @ResponseStatus(CREATED)
    public GameDto create() {
        val game = gameService.createNewGame();
        return map(game);
    }

    @GetMapping(ENDPOINT_ID)
    public GameDto getById(@PathVariable(PARAM_ID) long id) {
        val game = gameService.getById(id);
        return map(game);
    }

    @PutMapping(ENDPOINT_MOVE)
    public GameDto move(@PathVariable(PARAM_ID) long gameId,
                        @PathVariable(PARAM_PIT_ID) int pitId) {
        val game = gameService.makeMove(gameId, pitId);
        return map(game);
    }

    private GameDto map(Game game) {
        val uri = locationById(game.getId());
        return new GameDto()
                .setId(game.getId())
                .setUri(uri)
                .setStatus(game.getPits().asMap());
    }
}
