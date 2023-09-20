package com.event.utility;

import com.event.exception.EventException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorInfo> exceptionHandler(MethodArgumentNotValidException exception) {
        ErrorInfo error = new ErrorInfo();
        error.setErrorMessage(exception.getMessage());

        String errorMsg = exception.getBindingResult().getAllErrors().stream().map(x -> x.getDefaultMessage())
                .collect(Collectors.joining(", "));

        error.setErrorMessage(errorMsg);
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<ErrorInfo>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EventException.class)
    public ResponseEntity<ErrorInfo> eventExceptionHandler(EventException exception) {
        ErrorInfo error = new ErrorInfo();

        error.setErrorMessage(exception.getMessage());
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<ErrorInfo>(error, HttpStatus.NOT_FOUND);
    }

}
