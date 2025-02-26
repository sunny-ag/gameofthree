package com.justeattakeaway.gameofthree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class GameofthreeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameofthreeApplication.class, args);
	}

}
