package com.ll.chap_03;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//코드 수정 반영 안 될 때 재실행하기(f5 안 먹힘)

@Controller
public class HomeController {

    // http://localhost:8090/a
    // http://localhost:8090/a?age=23&id=1
    @GetMapping("a")
    @ResponseBody
    public String hello(String age, String id){
        return "안녕하세요, %s번 사람의 나이는 %s 세 입니다".formatted(id, age);
    }

    @GetMapping("b")
    @ResponseBody
    public String plus(
            @RequestParam("a") int num1,
            @RequestParam("b") int num2,
            @RequestParam(name = "c", defaultValue = "0") int num3
    ){
        // http://localhost:8090/b?a=14&b=17&c=7

        System.out.println("a = " + num1);
        System.out.println("b = " + num2);
        System.out.println("c = " + num3);

        String res1 = "a + b + c = %d\n".formatted(num1+num2+num3);

        //if (married == null) return "정보를 입력해주세요";
        //String res2 = married ? "기혼" : "미혼";

        return res1;

    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    public static class Person{
        //lombok으로 코드 줄이기... 어노테이션 붙이기(게터/세터/초기화 등)

        private String name;
        private int age;
    }


    @GetMapping("person")
    @ResponseBody
    public String person(
            String name,
            int age
    ) {
        // http://localhost:8090/person?name=sue&age=23
        Person person = new Person(name, age);
        // Person person = new Person("yu",23);
        // Person person;

        return person.toString();
    }

    @GetMapping("i")
    @ResponseBody
    public List<Integer> i() {
        List<Integer> arr = List.of(10,20,30);

        return arr;
    }

    @GetMapping("j")
    @ResponseBody
    public Map<String, Object> j() {
        Map<String, Object> person = new HashMap<>();
        person.put("name","paul");
        person.put("age",23);

        return person;
    }

    @AllArgsConstructor
    @Getter
    @Builder
    @ToString
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static class Post {
        @ToString.Exclude
        @JsonIgnore
        @EqualsAndHashCode.Include
        private Long id;

        private LocalDateTime createDate;
        private LocalDateTime modifyDate;

        @Builder.Default
        private String subject = "제목 입니다.";
        private String body;
    }

    @GetMapping("/posts")
    @ResponseBody
    public List<Post> getPosts() {
        List<Post> posts = new ArrayList<>() {{
            add(new Post(1L, LocalDateTime.now(), LocalDateTime.now(), "제목 1", "내용 1"));
            add(new Post(2L, LocalDateTime.now(), LocalDateTime.now(), "제목 2", "내용 2"));
            add(new Post(3L, LocalDateTime.now(), LocalDateTime.now(), "제목 3", "내용 3"));
            add(new Post(4L, LocalDateTime.now(), LocalDateTime.now(), "제목 4", "내용 4"));
            add(new Post(5L, LocalDateTime.now(), LocalDateTime.now(), "제목 5", "내용 5"));
        }};

        return posts;
    }

    @SneakyThrows
    @GetMapping("/posts2")
    @ResponseBody
    public List<Post> getPosts2() {
        List<Post> posts = new ArrayList<>() {{
            //builder 사용 시에는 요소 순서 바뀌어도 ok
            add(
                    Post
                            .builder()
                            .id(1L)
                            .createDate(LocalDateTime.now())
                            .modifyDate(LocalDateTime.now())
                            .subject("제목 1")
                            .body("내용 1")
                            .build()
            );

            add(
                    Post
                            .builder()
                            .id(2L)
                            .createDate(LocalDateTime.now())
                            .modifyDate(LocalDateTime.now())
                            .subject("제목 2")
                            .body("내용 2")
                            .build()
            );

            add(
                    Post
                            .builder()
                            .id(3L)
                            .createDate(LocalDateTime.now())
                            .modifyDate(LocalDateTime.now())
                            .body("내용 3")
                            .build()
            );
        }};

        Thread.sleep(3000);
        System.out.println(posts);

        return posts;
    }
}
