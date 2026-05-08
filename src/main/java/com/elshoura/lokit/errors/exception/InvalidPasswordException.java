package com.elshoura.lokit.errors.exception;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Value
@EqualsAndHashCode(callSuper = true)
public class InvalidPasswordException extends RuntimeException {

    int code=109;
    String message;
    public InvalidPasswordException(String message) {
        super(message);
        this.message=message;
        log.error("Invalid Password Exception");
    }
}
