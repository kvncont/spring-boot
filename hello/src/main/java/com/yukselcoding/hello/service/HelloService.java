package com.yukselcoding.hello.service;

import com.yukselcoding.hello.entity.Hello;
import com.yukselcoding.hello.exception.NameNotProvidedException;
import com.yukselcoding.hello.repository.HelloRepository;
import com.yukselcoding.hello.response.HelloResponse;


public class HelloService {

    public static final String NAME_VALUE_NOT_PROVIDED = "name value not provided.";
    public static final String HELLO_FORMAT = "Hello, %s";

    private HelloRepository repository;

    public HelloService(HelloRepository repository) {
        this.repository = repository;
    }

    public HelloResponse hello(String name) throws NameNotProvidedException {

        if(name == null || name.equals(""))
            throw new NameNotProvidedException(NAME_VALUE_NOT_PROVIDED);
        Hello hello = repository.findByName(name);
        return HelloResponse.builder().statement(String.format(HELLO_FORMAT, hello.getName())).build();
    }
}
