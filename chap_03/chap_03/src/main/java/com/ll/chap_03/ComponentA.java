package com.ll.chap_03;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ComponentA {
    //autowired는 controller / component 어노테이션이 달려있을 때... RequiredArgsConstructor에 final 달면 생략 가능
    private final ComponentB componentB;
    private final ComponentC componentC;
    private final ComponentD componentD;
    private final ComponentE componentE;

    public String action() {
        return "ComponentA action / " + componentB.getAction();
    }
}
