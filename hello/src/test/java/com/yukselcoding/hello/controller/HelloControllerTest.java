package com.yukselcoding.hello.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.yukselcoding.hello.controller.HelloController;
import com.yukselcoding.hello.exception.NameNotProvidedException;
import com.yukselcoding.hello.request.HelloRequest;
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

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private HelloService helloService;

    @Test
    @DisplayName("Should check whether hello using GET method returns success")
    public void testHelloGETSuccess() throws Exception, NameNotProvidedException {
        String name = "Ozge";
        given(helloService.hello(name)).willReturn(HelloResponse.builder().statement(String.format(HelloService.HELLO_FORMAT, name)).build());
        mockMvc.perform(MockMvcRequestBuilders.get(String.format("/hello/%s", name)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("statement").value(String.format(HelloService.HELLO_FORMAT, name)));
    }


    @Test
    @DisplayName("Should check whether hello using GET method returns 405")
    public void testHelloGETNameNotProvided() throws Exception {
         mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
                 .andExpect(status().isMethodNotAllowed());
    }

    @Test
    @DisplayName("Should check whether hello using POST method returns success")
    public void testHelloPOSTSuccess() throws Exception, NameNotProvidedException {
        String name = "Ozge";
        given(helloService.hello(name)).willReturn(HelloResponse.builder().statement(String.format(HelloService.HELLO_FORMAT, name)).build());
        mockMvc.perform(MockMvcRequestBuilders.post("/hello")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(HelloRequest.builder().name(name).build())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("statement").value(String.format(HelloService.HELLO_FORMAT, name)));
    }

    @Test
    @DisplayName("Should check whether hello using POST method returns success")
    public void testHelloPOSTNameGivenEmptyString() throws Exception, NameNotProvidedException {
        given(helloService.hello("")).willThrow(new NameNotProvidedException(HelloService.NAME_VALUE_NOT_PROVIDED));
        mockMvc.perform(MockMvcRequestBuilders.post("/hello")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(HelloRequest.builder().name("").build())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("statement").value(HelloService.NAME_VALUE_NOT_PROVIDED));

    }

    @Test
    @DisplayName("Should check whether hello using POST method returns success")
    public void testHelloPOSTNameGivenNull() throws Exception, NameNotProvidedException {
        given(helloService.hello(null)).willThrow(new NameNotProvidedException(HelloService.NAME_VALUE_NOT_PROVIDED));
        mockMvc.perform(MockMvcRequestBuilders.post("/hello")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(HelloRequest.builder().name(null).build())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("statement").value(HelloService.NAME_VALUE_NOT_PROVIDED));

    }





}
