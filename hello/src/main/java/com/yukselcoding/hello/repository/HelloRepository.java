package com.yukselcoding.hello.repository;


import com.yukselcoding.hello.entity.Hello;
import org.springframework.stereotype.Repository;

@Repository
public class HelloRepository {
    public Hello findByName(String name) {
        return null;
    }
}
