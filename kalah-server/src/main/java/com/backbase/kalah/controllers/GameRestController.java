package com.backbase.kalah.controllers;

import com.backbase.kalah.dto.GameDto;
import com.backbase.kalah.model.Game;
import com.backbase.kalah.services.GameService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.backbase.kalah.controllers.ControllerConstants.ENDPOINT_ID;
import static com.backbase.kalah.controllers.ControllerConstants.PARAM_ID;

@RestController
@RequestMapping(GameRestController.ENDPOINT)
@RequiredArgsConstructor
public class GameRestController extends AbstractRestController {

    static final String ENDPOINT = "/games";

    private final GameService gameService;

    @PostMapping
    public GameDto create() {
        val game = gameService.createNewGame();
        return map(game);
    }

    @GetMapping(ENDPOINT_ID)
    public GameDto getById(@PathVariable(PARAM_ID) long id) {
        val game = gameService.getById(id);
        return map(game);
    }

    private GameDto map(Game game) {
        val uri = locationById(game.getId());
        return new GameDto()
                .setId(game.getId())
                .setUri(uri);
    }
}
