package com.ll.demo03.domain.home.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @Value("${custom.site.name}")
    private String siteName;
    @Value("${custom.secret.key}")
    private String secretKey;

    @GetMapping("/")
    @ResponseBody

    public String ShowMain(){ return "Hello World! " + siteName; }

    @GetMapping("/secretKey")
    @ResponseBody
    public String ShowSecretKey(){
        return "Secret Key : " + secretKey;

    }
}
