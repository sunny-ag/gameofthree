package com.justeattakeaway.gameofthree.infrastructure.config;

import com.justeattakeaway.gameofthree.domain.model.Game;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    @Value("${spring.kafka.key-serializer}")
    private String keySerializer;
    @Value("${spring.kafka.value-serializer}")
    private String valueSerializer;

    /**
     * Creates a ProducerFactory instance for Kafka producers.
     *
     * @return a ProducerFactory instance
     */
    @Bean
    public ProducerFactory<String, Game> producerFactory() {
        Map<String, Object> configurationProperties = new HashMap<>();
        configurationProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configurationProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configurationProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(configurationProperties);
    }

    /**
     * Creates a KafkaTemplate instance for sending messages to Kafka topics.
     *
     * @return a KafkaTemplate instance
     */
    @Bean
    public KafkaTemplate<String, Game> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }}
