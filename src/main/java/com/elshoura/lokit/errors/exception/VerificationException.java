package com.elshoura.lokit.errors.exception;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Value
@EqualsAndHashCode(callSuper = true)
public class VerificationException extends RuntimeException {
    int code=110;
    String message;
    public VerificationException(String message) {
        super(message);
        this.message=message;
        log.error(message);
    }
}
