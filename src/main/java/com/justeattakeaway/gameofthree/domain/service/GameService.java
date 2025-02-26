package com.justeattakeaway.gameofthree.domain.service;

import com.justeattakeaway.gameofthree.application.util.AppConstants;
import com.justeattakeaway.gameofthree.domain.exception.PlayerGenericException;
import com.justeattakeaway.gameofthree.domain.model.Game;
import com.justeattakeaway.gameofthree.domain.model.Player;
import com.justeattakeaway.gameofthree.infrastructure.event.GameEventProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class GameService {

    private GameEventProducer gameEventProducer;

    @Autowired
    public GameService(GameEventProducer gameEventProducer) {
        this.gameEventProducer = gameEventProducer;
    }

    /**
     * Starts a new game with the given initial value and player names.
     *
     * @param initialValue the initial value of the game
     * @param playerNames  the names of the players
     * @return the started game
     * @throws PlayerGenericException if there are less than 2 players
     */
    public Game startGame(long initialValue, List<String> playerNames) {
        if(playerNames.size() < 2)
            throw new PlayerGenericException("At least 2 players are required");
        if (initialValue < 2)
            throw new PlayerGenericException("Initial value must be at least 2");

        List<Player> players = playerNames.stream().map(name -> new Player(name)).toList();
        Game game = new Game(initialValue,players);
        gameEventProducer.produceGameEvent(game);
        return game;
    }

    /**
     * Process the next move in the game.
     *
     * @param game the current state of the game
     */
    public void processMove(Game game) {;
        if(game.isGameOver())
            return;
        Game newGame = game.playNextMove();
        gameEventProducer.produceGameEvent(newGame);
    }
}
