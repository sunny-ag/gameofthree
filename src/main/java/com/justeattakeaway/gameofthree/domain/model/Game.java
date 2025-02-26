package com.justeattakeaway.gameofthree.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.justeattakeaway.gameofthree.application.util.AppConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

@Data
@ToString
@Slf4j
@NoArgsConstructor
public class Game {

    private UUID id;

    private long currentValue;

    private String status;

    private List<Player> players;

    private Player currentPlayer;

    public Game(long initialValue, List<Player> players) {
        this.id = UUID.randomUUID();
        this.currentValue = initialValue;
        this.status = AppConstants.GAME_IN_PROGRESS_STATUS;
        this.players = players;
        this.currentPlayer = players.get(0);
    }

    /**
     * Checks if the game is over.
     *
     * The game is considered over when the current value reaches 1.
     *
     * @return true if the game is over, false otherwise
     */
    @JsonIgnore
    public boolean isGameOver() {
        return currentValue == 1;
    }

    /**
     * Plays the next move in the game.
     *
     * This method updates the current value based on the game's rules, checks if the game is over,
     * and assigns the next player if the game is not over.
     *
     * @return the updated game state
     */
    public Game playNextMove() {
        int adjustment = (currentValue % 3 == 0) ? 0 : (currentValue % 3 == 1) ? -1 : 1;
        this.currentValue = (currentValue + adjustment) / 3;
        if (isGameOver()) {
            this.status = AppConstants.GAME_COMPLETD_STATUS;
            log.info("Game completed, and the winner is {}", this.currentPlayer.getName());
            return this;
        }
        assignNextPlayer();
        return this;
    }

    /**
     * Assigns the next player in the game.
     *
     * This method finds the index of the current player in the list of players,
     * calculates the index of the next player, and updates the current player,
     * if it reaches the end of the list, it will start from the first player.
     */
    public void assignNextPlayer() {
        int currentIndex = players.indexOf(currentPlayer);
        int nextIndex = (currentIndex + 1) % players.size();
        this.currentPlayer = players.get(nextIndex);
    }

}
