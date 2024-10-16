package com.cyj.shorty.config;

import com.cyj.shorty.util.DuplicationShortUrlException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler({DuplicationShortUrlException.class})
    public ResponseEntity<String> handleDuplicationShortUrlException(DuplicationShortUrlException ex){
        String errorMessage = ex.getMessage();
        log.error(errorMessage);

        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex){
        String errorMessage = ex.getMessage();
        log.error(errorMessage);

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<List<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        final List<FieldError> errorMessages = ex.getBindingResult().getFieldErrors();
        final List<String> errorMessage = errorMessages.stream()
                        .map(error -> error.getDefaultMessage())
                        .toList();
        log.error(errorMessage.toString());

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
