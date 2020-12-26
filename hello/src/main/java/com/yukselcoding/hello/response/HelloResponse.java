package com.yukselcoding.hello.response;


import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class HelloResponse {
    private String statement;
}
