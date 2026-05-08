package com.elshoura.lokit.utils.mapper;

import com.elshoura.lokit.errors.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;


@Slf4j
public class ValidationErrorMapper {

    public ValidationErrorMapper() {
        throw new IllegalStateException("ValidationErrorMapper class cannot be instantiated ");
    }
    public static ErrorResponse validateError(ObjectError objectError) {

        String errorMessage = ((FieldError)objectError).getField();
        String errorDetails = objectError.getDefaultMessage();


        log.error("Validation  massage on {}",errorMessage);
        log.error("Validation massage on {}",errorDetails);

        return new ErrorResponse(errorMessage,errorDetails);

    }
}
