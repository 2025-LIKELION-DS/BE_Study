package com.example.chapter03;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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