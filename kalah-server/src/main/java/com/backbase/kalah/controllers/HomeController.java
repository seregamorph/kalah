package com.backbase.kalah.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Home")
@Controller
@RequestMapping("/")
public class HomeController {

    static final String SWAGGER_UI_REDIRECT = "redirect:swagger-ui.html";

    @Operation(summary = "Create a game")
    @GetMapping
    String doGet() {
        return SWAGGER_UI_REDIRECT;
    }

}
