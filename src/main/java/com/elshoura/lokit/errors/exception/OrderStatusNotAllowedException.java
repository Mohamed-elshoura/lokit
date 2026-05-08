package com.elshoura.lokit.errors.exception;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Value
@EqualsAndHashCode(callSuper = true)
public class OrderStatusNotAllowedException extends RuntimeException {
    int code = 108;
    String description;

    public OrderStatusNotAllowedException(String description) {
        super(description);
        this.description = description;
        log.error("OrderStatusNotAllowedException");
    }
}
