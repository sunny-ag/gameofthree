package com.justeattakeaway.gameofthree.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
@NoArgsConstructor
public class Player {

    private UUID id;

    private String name;

    public Player(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }
}
