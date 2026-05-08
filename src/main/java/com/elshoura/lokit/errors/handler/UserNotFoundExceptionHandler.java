package com.elshoura.lokit.errors.handler;

import com.elshoura.lokit.errors.dto.ErrorDetails;
import com.elshoura.lokit.errors.exception.UserAlreadyExistException;
import com.elshoura.lokit.errors.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.elshoura.lokit.utils.timming.TimeStamp.currentTimestampOfUtc;

@ControllerAdvice
public class UserNotFoundExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleUserAlreadyExistException(UserAlreadyExistException ex) {

        ErrorDetails errorDetails= new ErrorDetails(ex.getCode(),ex.getMessage(),currentTimestampOfUtc());

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

}
