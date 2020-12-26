package com.yukselcoding.hello.exception;


import com.yukselcoding.hello.response.HelloResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HelloControllerAdvice {
    @ExceptionHandler(NameNotProvidedException.class)
    public ResponseEntity<?> handleNameNotProvidedException(NameNotProvidedException e) {
        return new ResponseEntity<>(HelloResponse.builder().statement(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }
}
