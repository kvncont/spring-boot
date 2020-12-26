package com.yukselcoding.hello.request;


import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HelloRequest {
    private String name;
}
