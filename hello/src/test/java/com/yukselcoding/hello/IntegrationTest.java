package com.yukselcoding.hello;


import com.yukselcoding.hello.request.HelloRequest;
import com.yukselcoding.hello.response.HelloResponse;
import com.yukselcoding.hello.service.HelloService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment=RANDOM_PORT)
public class IntegrationTest {


    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testHelloSuccess() {
        // arrange
        String name = "Ozge";
        String statement = String.format(HelloService.HELLO_FORMAT, name);
        // act
        ResponseEntity<HelloResponse> response = testRestTemplate
                .getForEntity(String.format("/hello/%s", name), HelloResponse.class);
        // assert
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getStatement(), statement);
    }
}
