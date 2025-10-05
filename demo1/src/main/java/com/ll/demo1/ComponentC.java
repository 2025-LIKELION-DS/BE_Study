package com.ll.demo1;

import org.springframework.stereotype.Component;

@Component
public class ComponentC {
    ComponentC() {
        System.out.println("ComponentC 객체 생성됨");
    }
}