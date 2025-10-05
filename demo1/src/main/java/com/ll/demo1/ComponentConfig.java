package com.ll.demo1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 내가 만든 클래스가 아닐 때 / 설정값 임의로 넣기
@Configuration // +@Bean -> @Component 안 넣어도 자동으로 읽음
public class ComponentConfig {
    @Bean
    public ComponentC componentC() {
        return new ComponentC();
    }

    @Bean
    public ComponentD componentD() {
        return new ComponentD();
    }

    @Bean
    public ComponentE componentE() {
        return new ComponentE();
    }
}