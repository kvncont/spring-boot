package com.yukselcoding.hello.exception;


import com.yukselcoding.hello.response.HelloResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Log4j2
@ControllerAdvice
public class HelloControllerAdvice {
    @ExceptionHandler(NameNotProvidedException.class)
    public ResponseEntity<?> handleNameNotProvidedException(NameNotProvidedException e) {
        log.info(String.format("Exception occurred: %s", e.getMessage()));
        return new ResponseEntity<>(HelloResponse
                .builder()
                .statement(e.getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }
}
