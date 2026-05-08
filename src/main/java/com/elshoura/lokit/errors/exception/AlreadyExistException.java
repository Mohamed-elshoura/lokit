package com.elshoura.lokit.errors.exception;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Value
@EqualsAndHashCode(callSuper = true)
public class AlreadyExistException extends RuntimeException {
      int code= 103;
      String description;

    public AlreadyExistException(String Description) {

        super(Description);
        this.description = Description;
        log.error(this.description);

    }
}
