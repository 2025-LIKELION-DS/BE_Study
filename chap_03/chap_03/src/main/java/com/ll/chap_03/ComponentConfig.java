package com.ll.chap_03;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 한 번에 여러 컴포넌트 생성
@Configuration
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
