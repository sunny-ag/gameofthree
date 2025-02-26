package com.justeattakeaway.gameofthree.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameRequest {
    private long initialValue;
    private List<String> playerNames;
}
