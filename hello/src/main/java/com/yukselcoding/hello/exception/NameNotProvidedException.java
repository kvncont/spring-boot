package com.yukselcoding.hello.exception;

import lombok.Getter;

@Getter
public class NameNotProvidedException extends Throwable {
    private String message;
    public NameNotProvidedException(String message) {
        super(message);
        this.message = message;
    }
}
