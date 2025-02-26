# Overview
 The game begins when a player generates a random whole number and sends it to the second player to initiate the game. The receiving player must then adjust the number by adding either -1, 0, or 1 to make it divisible by 3, after which they divide it by 3. The resulting whole number is then sent back to the original sender. This process continues, following the same rules, until one of the players reaches the number 1, concluding the game. The Game of Three is a coding challenge implemented using Spring Boot, Kafka, and the Domain-Driven Design (DDD) pattern.
 
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
    - GameController
  * util
    - AppConstants
   
# Game Flow

- A user sends a POST request with an initial random number and a list of player names.
- The application generates a unique game ID for the request.
- Before sending the response, the game request is published to Kafka.
- The game logic is processed asynchronously, continuing until the number reaches 1.
- The initial game ID is returned in the API response to the user.
- Kafka consumers handle the game progression, applying the game rules iteratively.

# Prerequisites
 - Java 17
 - Docker & Docker Compose

# Steps to Run Locally
 - Clone the repository
    * git clone -b main https://github.com/sunny-ag/gameofthree.git 
    * cd gameofthree
 - Run Docker Compose
    * docker compose up -d
 - Build Application Jar File
    * .\gradlew clean build
 - Run the application
    * .\gradlew bootRun
  
# Sample Request & URL
 - URL
   * localhost:2020/game-of-three/api/game/start
 - Sample Request Body
   {
    "initialValue": 56,
    "playerNames": [
        "Alice",
        "Bob"
    ]
   } 
