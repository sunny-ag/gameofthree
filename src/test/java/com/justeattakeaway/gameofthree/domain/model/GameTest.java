package com.justeattakeaway.gameofthree.domain.model;

import com.justeattakeaway.gameofthree.application.util.AppConstants;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void testIsGameOver() {
        Game game = new Game(1, List.of(new Player("Alice"), new Player("Bob")));
        assertTrue(game.isGameOver());

        game = new Game(11, List.of(new Player("Alice"), new Player("Bob")));
        assertFalse(game.isGameOver());
    }

    @Test
    public void testPlayNextMove() {
        Game game = new Game(20, List.of(new Player("Alice"), new Player("Bob")));
        Game newGame = game.playNextMove();
        assertEquals(7, newGame.getCurrentValue());

        game = new Game(4, List.of(new Player("Alice"), new Player("Bob")));
        newGame = game.playNextMove();
        assertEquals(AppConstants.GAME_COMPLETD_STATUS, newGame.getStatus());
    }

    @Test
    public void testAssignNextPlayer() {
        Game game = new Game(10, List.of(new Player("Alice"), new Player("Bob")));
        Player initialPlayer = game.getCurrentPlayer();
        game.assignNextPlayer();
        Player newPlayer = game.getCurrentPlayer();
        assertNotEquals(initialPlayer, newPlayer);
        assertEquals("Bob", newPlayer.getName());

        game = new Game(10, List.of(new Player("Alice"), new Player("Bob"), new Player("Eve")));
        initialPlayer = game.getCurrentPlayer();
        game.assignNextPlayer();
        game.assignNextPlayer();
        newPlayer = game.getCurrentPlayer();
        assertNotEquals(initialPlayer, newPlayer);
        game.assignNextPlayer();
        newPlayer = game.getCurrentPlayer();
        assertEquals("Alice", newPlayer.getName());
    }

}
