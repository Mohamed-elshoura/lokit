package com.elshoura.lokit.errors.handler;

import com.elshoura.lokit.errors.dto.ErrorDetails;
import com.elshoura.lokit.errors.exception.QuantityExceedsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.elshoura.lokit.utils.timming.TimeStamp.currentTimestampOfUtc;

@ControllerAdvice
public class QuantityExceedsExceptionHandler {

    @ExceptionHandler(QuantityExceedsException.class)
    public ResponseEntity<ErrorDetails> handleQuantityExceedsException(final QuantityExceedsException ex) {

        ErrorDetails errorDetails = new ErrorDetails(ex.getCode(),ex.getDetails(),currentTimestampOfUtc());

        return  new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

}
