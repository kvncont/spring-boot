package com.yukselcoding.hello.service;

import com.yukselcoding.hello.exception.NameNotProvidedException;
import com.yukselcoding.hello.response.HelloResponse;
import org.springframework.stereotype.Service;


@Service
public class HelloService {

    public static final String NAME_VALUE_NOT_PROVIDED = "name value not provided.";
    public static final String HELLO_FORMAT = "Hello, %s";

    public HelloResponse hello(String name) throws NameNotProvidedException {
        if(name.equals(""))
            throw new NameNotProvidedException(NAME_VALUE_NOT_PROVIDED);
        return HelloResponse.builder().statement(String.format(HELLO_FORMAT, name)).build();
    }
}
