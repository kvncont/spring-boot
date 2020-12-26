package com.yukselcoding.hello.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NameNotProvidedException extends Throwable {
    private String message;
    public NameNotProvidedException(String message) {
        super(message);
        this.message = message;
    }
}
