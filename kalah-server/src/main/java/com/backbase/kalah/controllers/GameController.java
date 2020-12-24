package com.backbase.kalah.controllers;

import com.backbase.kalah.dto.GameDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(GameController.ENDPOINT)
public class GameController {

    static final String ENDPOINT = "/games";

    @GetMapping("/{id}")
    public GameDto getById(@PathVariable("id") long id) {
        return new GameDto()
                .setId(id);
    }


}
