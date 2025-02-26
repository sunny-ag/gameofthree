package com.justeattakeaway.gameofthree.infrastructure.event;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.justeattakeaway.gameofthree.domain.model.Game;
import com.justeattakeaway.gameofthree.domain.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Slf4j
@Component
public class GameEventConsumer {

    private GameService gameService;

    @Autowired
    public GameEventConsumer(GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * Consumes a game context from the Kafka topic and processes the next move in the game.
     *
     * @param game the current state of the game
     */
    @KafkaListener(topics = "${kafka.game.topic.name}", groupId = "${kafka.game.group.id}", containerFactory = "gameListenerContainerFactory")
    public void consumeGameContext(Game game) throws JsonProcessingException {
        log.info("Received game context: {}", game);
        gameService.processMove(game);
    }
}
