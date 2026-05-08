package com.elshoura.lokit.errors.handler;

import com.elshoura.lokit.errors.dto.ErrorDetails;
import com.elshoura.lokit.errors.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.elshoura.lokit.utils.timming.TimeStamp.currentTimestampOfUtc;

@ControllerAdvice
public class NotFoundExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDetails> handleBrandNotFoundException(NotFoundException ex){

        ErrorDetails errorDetails=new ErrorDetails(ex.getCode(), ex.getDescription(), currentTimestampOfUtc());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);

    }
}
