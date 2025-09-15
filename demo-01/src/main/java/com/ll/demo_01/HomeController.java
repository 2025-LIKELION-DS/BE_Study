package com.ll.demo_01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("a")
    @ResponseBody /* 이거 해야 화면(브라우저) 에 뜸*/
    public String hello(
            String age,
            String id
    ) {
        return "%s번 사람의 나이는 %s살 입니다" .formatted(id,age);
    }

    @GetMapping("b") //액션 요청(편지(글)) - url에 의해서 호출됨
    @ResponseBody /* 이거 해야 화면(브라우저) 에 뜸*/
    //http://localhost:8090/b?a=20&b=10
    public String plus(
            @RequestParam("a") int num1,
            @RequestParam("b") int num2,
            @RequestParam(name="c" , defaultValue = "0") int num3
    ) {
        System.out.println("num1 = " + num1 );
        System.out.println("num2 = " + num2 );
        System.out.println("num3 = " + num3 );

        return "a+b+c= %d" .formatted(num1+ num2+num3);
    }
    @GetMapping("c")
    @ResponseBody
    public String c(
            boolean married
    ){
        return married ? "결혼" : "미혼" ;
    }

    @GetMapping("d")
    @ResponseBody
    public String d(
            Boolean married
    ){
        if( married == null ) return "정보를 입력해주세요.";
        return married ? "결혼" : "미혼" ;
    }

    public  static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    @GetMapping("person1")
    @ResponseBody
    public String person(
            String name,
            int age
    ){
        Person person = new Person(name,age);

        return person.toString();
    }
    @GetMapping("person2")
    @ResponseBody
    public String person(
            Person person
    ){
        return person.toString();
    }
    @GetMapping("e")
    @ResponseBody
    public int e(
    ){
        return 10;
    }

    @GetMapping("f")
    @ResponseBody
    public boolean f(
    ){
        return true;
    }


}
