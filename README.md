﻿# Overview
 The Game of Three is a coding challenge implemented using Spring Boot, Kafka, and the Domain-Driven Design (DDD) pattern.
 
# Technologies Used
 - Java 17
 - Spring boot 3.3.9
 - Confluent Kafka
 - JUnit 5
 - Mockito

# Architecture
This project follows the Domain-Driven Design (DDD) principles:\
1) Domain Layer (Core Busineess Logic)
   * exception
     - PlayerGenericException
   * model
     - Game
     - Player
     - GameRequest
     - ErrorMessage
   * service
     - GameService
2) Infrastructure Layer (Handles external requests like kafka pub/sub)
  * config
    - AppConfig
    - KafkaConsumerConfig
    - KafkaProducerConfig
  * event
    - GameEventConsumer
    - GameEventProducer
3) Application Layer (Handles API requests and interacts with the domain layer)
  * controller
    -GameController
  * util
    -AppConstants
