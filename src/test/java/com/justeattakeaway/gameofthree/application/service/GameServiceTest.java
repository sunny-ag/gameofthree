package com.justeattakeaway.gameofthree.application.service;

import com.justeattakeaway.gameofthree.domain.exception.PlayerGenericException;
import com.justeattakeaway.gameofthree.domain.model.Game;
import com.justeattakeaway.gameofthree.domain.model.Player;
import com.justeattakeaway.gameofthree.domain.service.GameService;
import com.justeattakeaway.gameofthree.infrastructure.event.GameEventProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    @Mock
    private GameEventProducer gameEventProducer;

    @InjectMocks
    private GameService gameService;

    @Test
    public void testStartGame_WithValidInput_ReturnsGame() {
        long initialValue = 10;
        List<String> playerNames = Arrays.asList("Alice", "Bob");

        Game game = gameService.startGame(initialValue, playerNames);

        assertEquals(initialValue, game.getCurrentValue());
        assertEquals(playerNames.size(), game.getPlayers().size());
        Mockito.verify(gameEventProducer).produceGameEvent(game);
    }

    @Test
    public void testStartGame_WithLessThanTwoPlayers_ThrowsPlayerGenericException() {
        long initialValue = 10;
        List<String> playerNames = Arrays.asList("Alice");

        assertThrows(PlayerGenericException.class, () -> gameService.startGame(initialValue, playerNames));
    }

    @Test
    public void testStartGame_WithInitialValueLessThanTwo_ThrowsPlayerGenericException() {
        long initialValue = 1;
        List<String> playerNames = Arrays.asList("Alice", "Bob");

        assertThrows(PlayerGenericException.class, () -> gameService.startGame(initialValue, playerNames));
    }

    @Test
    public void testProcessMove_CallsProduceGameEvent() {
        Game game = new Game(10, Arrays.asList(new Player("Alice"), new Player("Bob")));
        gameService.processMove(game);

        Mockito.verify(gameEventProducer).produceGameEvent(game.playNextMove());
    }
}
