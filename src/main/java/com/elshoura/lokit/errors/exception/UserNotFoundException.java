package com.elshoura.lokit.errors.exception;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Value
@EqualsAndHashCode(callSuper = true)
public class UserNotFoundException extends RuntimeException {
    int code=102;
    String details;

    public UserNotFoundException(String details) {
        super(details);
        this.details = details;
        log.error(details);
    }
}
