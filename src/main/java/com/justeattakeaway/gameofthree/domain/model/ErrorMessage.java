package com.justeattakeaway.gameofthree.domain.model;

import lombok.Data;

@Data
public class ErrorMessage {
    private String errorMessage;
    private long errorCode;
}
