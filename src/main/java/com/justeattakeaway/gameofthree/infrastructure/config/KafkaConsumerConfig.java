package com.justeattakeaway.gameofthree.infrastructure.config;

import com.justeattakeaway.gameofthree.domain.model.Game;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    /**
     * Creates a ConsumerFactory instance for Kafka consumers.
     *
     * @return a ConsumerFactory instance
     */
    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> configurationProperties = new HashMap<>();
        configurationProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configurationProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        configurationProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class.getName());

        configurationProperties.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.justeattakeaway.gameofthree.domain.model.Game");

        return new DefaultKafkaConsumerFactory<String, String>(configurationProperties);
    }

    /**
     * Creates a ConcurrentKafkaListenerContainerFactory instance for Kafka listeners.
     *
     * @return a ConcurrentKafkaListenerContainerFactory instance
     */
    @Bean("gameListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        concurrentKafkaListenerContainerFactory.setConsumerFactory(consumerFactory());

        return concurrentKafkaListenerContainerFactory;
    }
}
