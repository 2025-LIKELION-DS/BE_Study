package com.example.chapter03_2;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Todo {
    public long id;
    private String body;
}
