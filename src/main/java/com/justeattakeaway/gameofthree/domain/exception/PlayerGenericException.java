package com.justeattakeaway.gameofthree.domain.exception;

public class PlayerGenericException extends RuntimeException{
    public PlayerGenericException() {
    }

    public PlayerGenericException(String message) {
        super(message);
    }
}
