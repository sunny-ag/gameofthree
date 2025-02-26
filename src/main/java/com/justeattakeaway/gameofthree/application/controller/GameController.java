package com.justeattakeaway.gameofthree.application.controller;

import com.justeattakeaway.gameofthree.domain.model.Game;
import com.justeattakeaway.gameofthree.domain.model.GameRequest;
import com.justeattakeaway.gameofthree.domain.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
public class GameController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/start")
    public Game startGame(@RequestBody GameRequest gameRequest) {
        return gameService.startGame(gameRequest.getInitialValue(), gameRequest.getPlayerNames());
    }

    @PostMapping("/game")
    public Game test(@RequestBody Game game) {
        System.out.println("Test");
        return game;
    }
}
