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

//서버용 주석 - 아래는 모두 고객x서버o에서
@Controller
public class HomeController {
    // 액션메서드
    @GetMapping("a")    // http요청
    @ResponseBody       // 리턴값을 브라우저에
    public String hello(
            String age, String id // js와 java의 공용어는 string
    ) {
        return "Hello %s years old no.%s".formatted(age,id);
    }

    @GetMapping("b") // 액션 요청
    @ResponseBody
    // http://localhost:8090/b?a=20&b=30
    public String plus(
            @RequestParam("a") int num1, // 원래 주고받는건 string -jackson-> 형변환
            @RequestParam("b") int num2 //String num2Str
    ) {
//        int num1 = Integer.parseInt(num1Str);
//        int num2 = Integer.parseInt(num2Str);

        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);

        return "a + b = %d".formatted(num1 + num2);
    }
    @GetMapping("d")
    @ResponseBody
    public String d(
            Boolean married
    ) {
        if ( married == null ) return "정보를 입력해주세요.";
        return married ? "결혼" : "미혼";
    }

    @Getter
    @Setter // 모든 필드에 적용이 됨
    public static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }

//        public int getAge() {
//            return age;
//        }
//
//        public void setAge(int age) {
//            this.age = age;
//        }

        // 이것도 get,set과 마찬가지로 생략해서 대체 가능
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
    ) {
        Person person = new Person(name, age);

        return person.toString();
    }

    @GetMapping("person2")
    @ResponseBody
    public String person2(
            Person person
    ) {
        return person.toString();
    }

    @GetMapping("e")
    @ResponseBody
    public int e() {
        int age = 10;

        return age;
    }

    @GetMapping("f")
    @ResponseBody
    public boolean f() {
        boolean married = true;

        return married;
    }

    @GetMapping("g")
    @ResponseBody
    public Person g() {
        Person person = new Person("Paul", 22);

        return person;
    }

    @GetMapping("h")
    @ResponseBody
    public int[] h() {
        int[] arr = new int[] {10, 20, 30};

        return arr;
    }

    @GetMapping("i")
    @ResponseBody
    public List<Integer> i() {
        List<Integer> arr = List.of(10, 20, 30);

        return arr;
    }

    @GetMapping("j")
    @ResponseBody
    public Map<String, Object> j() {
        Map<String, Object> person = new HashMap<>();
        person.put("age", 23);
        person.put("name", "Paul");

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

    @GetMapping("/posts2")
    @ResponseBody
    public List<Post> getPosts2() {
        List<Post> posts = new ArrayList<>() {{
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

        return posts;
    }

    @GetMapping("/posts/1")
    @ResponseBody
    public Post getPost() {
        Post post = Post
                .builder()
                .id(1L)
                .createDate(LocalDateTime.now())
                .modifyDate(LocalDateTime.now())
                .subject("제목 1")
                .body("내용 1")
                .build();

        System.out.println(post);

        return post;
    }

    @SneakyThrows
    @GetMapping("/posts/2")
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

        Thread.sleep(5000);

        System.out.println(post);

        return post; // Post 객체를 리턴->JSON으로 바뀌어서 브라우저에
    }
}