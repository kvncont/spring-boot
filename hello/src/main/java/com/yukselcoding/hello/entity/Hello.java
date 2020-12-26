package com.yukselcoding.hello.entity;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Hello {
    private Long id;
    private String name;

    public Hello(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Hello(String name) {
        this.name = name;
    }
}
