package com.yukselcoding.hello.service;


import com.yukselcoding.hello.entity.Hello;
import com.yukselcoding.hello.exception.NameNotProvidedException;
import com.yukselcoding.hello.repository.HelloRepository;
import com.yukselcoding.hello.response.HelloResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class HelloServiceTest {

    @Mock
    private HelloRepository repository;

    private HelloService service;


    @BeforeEach
    public void setUp() {
        service = new HelloService(repository);
    }

    @Test
    @DisplayName("Should check if hello method is a success")
    public void testHello_NameProvided() throws NameNotProvidedException {
        String name = "Ozge";
        given(repository.findByName(name)).willReturn(new Hello(name));
        HelloResponse response = service.hello(name);
        assertEquals(response.getStatement(), String.format(HelloService.HELLO_FORMAT, name));
    }

    @Test
    @DisplayName("Should check exception is thrown when provided empty string as a name")
    public void testHello_NameNotProvided() {
        String name = "";
        assertThrows(NameNotProvidedException.class, () -> service.hello(name));
    }

    @Test
    @DisplayName("Should check exception is thrown when provided null as a name")
    public void testHello_NameProvidedNull() {
        String name = null;
        assertThrows(NameNotProvidedException.class, () -> service.hello(name));
    }
}
