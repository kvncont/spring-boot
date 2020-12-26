package com.yukselcoding.hello.controller;


import com.yukselcoding.hello.exception.NameNotProvidedException;
import com.yukselcoding.hello.request.HelloRequest;
import com.yukselcoding.hello.response.HelloResponse;
import com.yukselcoding.hello.service.HelloService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    private HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }


    @GetMapping("/hello/{name}")
    private ResponseEntity<HelloResponse> hello(@PathVariable String name) throws NameNotProvidedException {
        return new ResponseEntity<>(helloService.hello(name), HttpStatus.OK);
    }

    @PostMapping("/hello")
    private ResponseEntity<HelloResponse> hello(@RequestBody HelloRequest request) throws NameNotProvidedException {
        return new ResponseEntity<>(helloService.hello(request.getName()), HttpStatus.OK);
    }
}
