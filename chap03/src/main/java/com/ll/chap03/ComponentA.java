package com.ll.chap03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComponentA {
    @Autowired
    private ComponentB componentB;
    @Autowired
    private ComponentC componentC;
    @Autowired
    private ComponentD componentD;
    @Autowired
    private ComponentE componentE;

    public String action() {
       return "ComponentA action / " + componentB.getAction();
    }
}
