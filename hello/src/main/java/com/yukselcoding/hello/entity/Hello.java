package com.yukselcoding.hello.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Hello {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    public Hello(String name) {
        this.name = name;
    }
}
