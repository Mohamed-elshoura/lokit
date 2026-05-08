package com.elshoura.lokit.errors.exception;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Value
@EqualsAndHashCode(callSuper = true)
public class UserForbiddenException extends RuntimeException{

    int code = 107;
    String message;
    public UserForbiddenException( String message) {
        super(message);
        this.message = message;
        log.error(message);
    }
}
