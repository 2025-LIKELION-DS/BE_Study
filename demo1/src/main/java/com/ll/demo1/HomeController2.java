package com.ll.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// HomeController2는 웹 요청을 처리 - 직원
// 실제 로직(액션 메서드)은 ComponentA가 담당 - 주방 - 재사용 가능

@Controller
@RequestMapping("/home2") // 기본 url매핑 (GetMapping은 GET만)
public class HomeController2 {
    @Autowired // 스프링이 ComponentA 객체를 new 없이 자동으로 만들어줌
    private ComponentA componentA;

    // http://localhost:8090/home2/action1
    @GetMapping("/action1") // 주입받은 컴포넌트를 사용해서
    @ResponseBody
    public String action1() {
        return componentA.action();
    }
}