package com.justeattakeaway.gameofthree.application.controller;

import com.justeattakeaway.gameofthree.domain.model.Game;
import com.justeattakeaway.gameofthree.domain.model.GameRequest;
import com.justeattakeaway.gameofthree.domain.model.Player;
import com.justeattakeaway.gameofthree.domain.service.GameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class GameControllerTest {
    @Mock
    private GameService gameService;

    @InjectMocks
    private GameController gameController;

    @Test
    public void testStartGame() {
        GameRequest gameRequest = new GameRequest(56, Arrays.asList("Alice", "Bob"));
        Game expectedGame = new Game(10, Arrays.asList(new Player("Alice"), new Player("Bob")));
        Mockito.when(gameService.startGame(gameRequest.getInitialValue(), gameRequest.getPlayerNames())).thenReturn(expectedGame);

        Game response = gameController.startGame(gameRequest);

        assertEquals(expectedGame, response);
    }

    @Test
    public void testStartGame_GameServiceThrowsException() {
        GameRequest gameRequest = new GameRequest(1, Arrays.asList("Alice", "Bob"));
        Mockito.when(gameService.startGame(gameRequest.getInitialValue(), gameRequest.getPlayerNames())).thenThrow(new RuntimeException("Test exception"));
        try {
            gameController.startGame(gameRequest);
            fail("Expected exception was not thrown");
        } catch (Exception e) {
            assertEquals("Test exception", e.getMessage());
        }
    }
}
