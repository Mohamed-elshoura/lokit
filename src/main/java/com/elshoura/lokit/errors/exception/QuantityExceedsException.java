package com.elshoura.lokit.errors.exception;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Value
@EqualsAndHashCode(callSuper = true)
public class QuantityExceedsException extends RuntimeException {
    int code=106;
    String details;

    public QuantityExceedsException(String details) {
        super(details);
        this.details=details;
        log.error(details);
    }
}
