package com.elshoura.lokit.errors.exception;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Value
@EqualsAndHashCode(callSuper = true)
public class InvalidCredentialsException extends RuntimeException {

    int code=401;
    String message="Invalid Credentials";
    String details;
    public InvalidCredentialsException(String details) {
        super(details);
        this.details=details;
        log.error(details);
    }
}
