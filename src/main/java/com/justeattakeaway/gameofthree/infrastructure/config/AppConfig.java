package com.justeattakeaway.gameofthree.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

/**
 * Creates a new instance of the ObjectMapper bean in IOC container
 */
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
