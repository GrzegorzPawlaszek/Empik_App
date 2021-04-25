package com.gregpawlaszek.empikApp.config;

import com.gregpawlaszek.empikApp.exception.ExternalApiException;
import com.gregpawlaszek.empikApp.exception.UserLoginArgumentException;
import com.gregpawlaszek.empikApp.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(UserLoginArgumentException.class)
    ResponseEntity<ExceptionDetails> handleIncrementArgumentException(UserLoginArgumentException ex){
        ExceptionDetails exceptionDetails = new ExceptionDetails(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.badRequest().body(exceptionDetails);
    }

    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<ExceptionDetails> handleUserNotFoundException(UserNotFoundException ex){
        ExceptionDetails exceptionDetails = new ExceptionDetails(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.badRequest().body(exceptionDetails);
    }

    @ExceptionHandler(ExternalApiException.class)
    ResponseEntity<ExceptionDetails> handleExternalApiException(ExternalApiException ex){
        ExceptionDetails exceptionDetails = new ExceptionDetails(HttpStatus.GATEWAY_TIMEOUT.value(), HttpStatus.GATEWAY_TIMEOUT, ex.getMessage());
        return ResponseEntity.badRequest().body(exceptionDetails);
    }


    @Getter
    @AllArgsConstructor
    private static class ExceptionDetails{
        private int code;
        private HttpStatus status;
        private String message;
    }
}
