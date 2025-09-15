package com.ll.demo02;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private long todosLasId;
    private List<Todo> todos;

    public TodoController() {
        todos = new ArrayList<>();
    }

    @GetMapping("")
    public List<Todo> getTodos() {
        return todos;
    }

    @GetMapping("/{id}")
    public Todo getTodo(
            @PathVariable long id
    ) { //주소 자체에서 숫자 받기 ( todos/5 ) 하면 나옴
        return todos
                .stream()
                .filter(
                        todo -> todo.getId() == id
                )
                .findFirst()
                .orElse(null);
    }


    @GetMapping("/add")
    public Todo add(
            String body
    ){
        Todo todo = Todo
                .builder()
                .id(++todosLasId)
                .body(body)
                .build();

        todos.add(todo);

        return todo;
    }
    @GetMapping("/remove")
    public boolean remove(
            @PathVariable long id
    ){
        boolean removed = todos.removeIf((todo -> todo.getId() == id));

        return  removed;
    }
    @GetMapping("/modify/{id}")
    public boolean modify(
            @PathVariable long id,
            String body
    ){
        Todo todo = todos
                .stream()
                .filter(
                        _todo -> _todo.getId() == id
                )
                .findFirst()
                .orElse(null);
        if ( todo == null ) return false;

        todo.setBody(body);

        return true;
    }

}
