package com.elshoura.lokit.errors.exception;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Value
@EqualsAndHashCode(callSuper = true)
public class NotFoundException extends RuntimeException {

    int code= 104;
    String description;

    public NotFoundException(String description) {
        super(description);
        this.description = description;
        log.error(description);
    }
}
