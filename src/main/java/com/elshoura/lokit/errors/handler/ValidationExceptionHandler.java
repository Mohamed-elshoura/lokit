package com.elshoura.lokit.errors.handler;

import com.elshoura.lokit.errors.dto.ErrorResponse;
import com.elshoura.lokit.errors.dto.GeneralResponse;
import com.elshoura.lokit.utils.mapper.ValidationErrorMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

import static com.elshoura.lokit.utils.timming.TimeStamp.currentTimestampOfUtc;

@ControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GeneralResponse<ErrorResponse>> handleValidationExceptions(MethodArgumentNotValidException ex) {
      final List<ErrorResponse> errorResponse = ex.getBindingResult().getAllErrors()
               .stream()
               .map(ValidationErrorMapper::validateError)
               .toList();
        return new ResponseEntity(new GeneralResponse<>(200,errorResponse,currentTimestampOfUtc()), HttpStatus.BAD_REQUEST);

    }
}
