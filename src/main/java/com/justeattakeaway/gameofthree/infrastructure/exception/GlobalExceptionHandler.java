package com.justeattakeaway.gameofthree.infrastructure.exception;

import com.justeattakeaway.gameofthree.domain.exception.PlayerGenericException;
import com.justeattakeaway.gameofthree.domain.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * GlobalExceptionHandler is a REST controller advice that handles exceptions
 * thrown by the application. It provides a centralized way to handle exceptions
 * and return error responses to the client.
 *
 * @author Sandeep Ileni
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles PlayerGenericException exceptions thrown by the application.
     *
     * @param e the PlayerGenericException exception to be handled
     * @return an ErrorMessage object containing the error message and code
     */
    @ExceptionHandler(PlayerGenericException.class)
    public ErrorMessage handlePlayerAlreadyPresentException(PlayerGenericException e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorMessage(e.getMessage());
        errorMessage.setErrorCode(HttpStatus.BAD_REQUEST.value());

        return errorMessage;
    }
}
