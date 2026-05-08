package com.elshoura.lokit.errors.handler;

import com.elshoura.lokit.errors.dto.ErrorDetails;
import com.elshoura.lokit.errors.exception.InvalidPasswordException;
import com.elshoura.lokit.errors.exception.VerificationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.elshoura.lokit.utils.timming.TimeStamp.currentTimestampOfUtc;

@ControllerAdvice
public class VerificationExceptionHandler {

    @ExceptionHandler(VerificationException.class)
    public ResponseEntity<ErrorDetails> handleVerificationException(InvalidPasswordException ex) {

        ErrorDetails errorDetails=new ErrorDetails(ex.getCode(), ex.getMessage(), currentTimestampOfUtc());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
