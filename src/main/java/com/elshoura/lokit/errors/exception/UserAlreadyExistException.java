package com.elshoura.lokit.errors.exception;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Value
@EqualsAndHashCode(callSuper = true)
public class UserAlreadyExistException extends RuntimeException {
    int code=101;
    String details;

    public UserAlreadyExistException(String details) {
        super(details);
        this.details = details;
        log.error(details);
    }
}
