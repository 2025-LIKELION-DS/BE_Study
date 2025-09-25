package com.ll.chap_03_2;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Todo {

    private long id;
    private String body;
}
