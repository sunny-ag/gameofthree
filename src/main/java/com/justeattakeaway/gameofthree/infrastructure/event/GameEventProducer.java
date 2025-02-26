package com.justeattakeaway.gameofthree.infrastructure.event;

import com.justeattakeaway.gameofthree.application.util.AppConstants;
import com.justeattakeaway.gameofthree.domain.model.Game;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GameEventProducer {


    private final String TOPIC_NAME;
    private KafkaTemplate<String, Game> kafkaTemplate;

    @Autowired
    public GameEventProducer(@Value("${kafka.game.topic.name}") String topicName, KafkaTemplate<String, Game> kafkaTemplate) {
        this.TOPIC_NAME = topicName;
        this.kafkaTemplate = kafkaTemplate;
    }
    /**
     * Produces a game event by sending the game state to the Kafka topic.
     *
     * @param game the current state of the game
     */
    public void produceGameEvent(Game game) {
        log.info("Producing game event: {}", game.toString());
        if(!game.getStatus().equalsIgnoreCase(AppConstants.GAME_COMPLETD_STATUS)) {
            kafkaTemplate.send(TOPIC_NAME, game);
        }
    }
}
