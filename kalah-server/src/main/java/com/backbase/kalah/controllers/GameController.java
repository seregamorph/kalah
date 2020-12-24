package com.backbase.kalah.controllers;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games")
public class GameController {

    @GetMapping("/id")
    public Game getById(long id) {
        return new Game()
                .setId(id);
    }


    @Data
    public static class Game {
        private long id;
    }
}
