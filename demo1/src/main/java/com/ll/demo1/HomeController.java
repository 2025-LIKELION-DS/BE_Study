package com.ll.demo1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 액션 메서드가 리턴하는 것을 json(String) 형태로 변경

// 서버용 주석 - 아래는 모두 고객x서버o에서
// 창구직원 어노테이션 - 클래스 객체 자동 생성해서 요청 올 때마다 사용하는 컨트롤러
@Controller
public class HomeController {
    // 액션메서드 어노테이션 - 외부에서 호출 가능한 메서드
    @GetMapping("a")
    @ResponseBody // 브라우저에 리턴 어노테이션
    public String hello(
            String age,
            String id
    ) { // http://localhost:8080/a?age=22&id=1
        return "안녕하세요. %s번 사람의 나이는 %s살 입니다.".formatted(id, age);
    }

    @GetMapping("b")
    @ResponseBody
    public String plus( // 변수이름!=쿼리파라미터 ok
                        @RequestParam("a") int num1,
                        @RequestParam("b") int num2,
                        @RequestParam(name = "c", defaultValue = "0") int num3
    ) {
        return "a + b + c = %d".formatted(num1 + num2 + num3);
    }

    @GetMapping("c")
    @ResponseBody
    public String c(
            boolean married
    ) {
        return married ? "결혼" : "미혼";
    }

    @GetMapping("d")
    @ResponseBody
    public String d(
            Boolean married // null 허용
    ) { // 입력x true false
        if ( married == null ) return "정보를 입력해주세요.";
        return married ? "결혼" : "미혼";
    }

    // 롬북 - 코드를 더 짧게
    @Getter
    @Setter
    @ToString
    @AllArgsConstructor // 생성자 대신
    public static class Person {
        private String name;
        private int age;
    }


    @AllArgsConstructor
    @Getter // json으로 노출시키고 싶은 거
    @Builder // new 대신 빌더로 객체 생성 가능
    @ToString
    // 같은 객체로 취급<-
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static class Post {
        @ToString.Exclude // 출력 시 제외
        @JsonIgnore // json에서 제외
        @EqualsAndHashCode.Include // <-이 필드만 같으면
        private Long id;
        private LocalDateTime createDate;
        private LocalDateTime modifyDate;
        @Builder.Default // 빌더 쓸 때 디폴트
        private String subject = "제목입니다";
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

    @GetMapping("/posts2")
    @ResponseBody
    public List<Post> getPosts2() {
        List<Post> posts = new ArrayList<>() {{
            add( // 속성 순서 바꿔도 됨
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

        return posts;
    }

    @SneakyThrows // 지연
    @GetMapping("/posts/1")
    @ResponseBody
    public Post getPost2() {
        Post post = Post
                .builder()
                .id(2L)
                .createDate(LocalDateTime.now())
                .modifyDate(LocalDateTime.now())
                .subject("제목 2")
                .body("내용 2")
                .build();

        Thread.sleep(5000); // 5초

        System.out.println(post);

        return post;
    }




    @GetMapping("person1")
    @ResponseBody
    public String person(
            String name,
            int age
    ) {
        Person person = new Person(name, age);
        return person.toString(); // tostring 직접 호출해서 리턴
    }

    @GetMapping("person2")
    @ResponseBody
    public String person2(
            Person person // 쿼리 파라미터를 자동으로 객체로 모아줌
    ) {
        return person.toString();
    }

    @GetMapping("e")
    @ResponseBody
    public int e() { // jackson이 string으로 리턴
        int age = 10;

        return age;
    }

    @GetMapping("f")
    @ResponseBody
    public Person g() { // ,, 객체를 json으로
        Person person = new Person("Paul", 22);

        return person;
    }

    @GetMapping("g")
    @ResponseBody
    public Map<String, Object> j() {
        Map<String, Object> person = new HashMap<>();
        person.put("age", 23);
        person.put("name", "Paul");

        return person;
    }
}