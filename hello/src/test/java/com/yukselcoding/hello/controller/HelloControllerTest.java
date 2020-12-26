package com.yukselcoding.hello.controller;


import com.yukselcoding.hello.controller.HelloController;
import com.yukselcoding.hello.exception.NameNotProvidedException;
import com.yukselcoding.hello.response.HelloResponse;
import com.yukselcoding.hello.service.HelloService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HelloController.class)
public class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HelloService helloService;

    @Test
    @DisplayName("Should check whether hello returns success")
    public void testHelloSuccess() throws Exception, NameNotProvidedException {
        String name = "Ozge";
        given(helloService.hello(name)).willReturn(HelloResponse.builder().statement(String.format(HelloService.HELLO_FORMAT, name)).build());
        mockMvc.perform(MockMvcRequestBuilders.get(String.format("/hello/%s", name)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("statement").value(String.format(HelloService.HELLO_FORMAT, name)));
    }


    @Test
    @DisplayName("Should check whether hello returns exception in case name value not provided")
    public void testHelloNameNotProvided() throws Exception {
         mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
                 .andExpect(status().isNotFound());
     }
}
