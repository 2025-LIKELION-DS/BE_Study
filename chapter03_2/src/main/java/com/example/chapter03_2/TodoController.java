package com.example.chapter03_2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/todos")
public class TodoController {
    private long TodoLastId;
    private List<Todo> todos;

    public TodoController(){
        todos=new ArrayList<>();
    }

    @GetMapping("")
    public List<Todo> getTodos(){
        return todos;
    }

    @GetMapping("/{id}")
    //@GetMapping("/detail")
    public Todo getTodo(long id){
        return todos
                .stream().
                filter(
                        todo -> todo.getId()==id
                )
                .findFirst()
                .orElse(null);
    }

    @GetMapping("/add")
    public Todo add(
            String body
    ){
        Todo todo=Todo
                .builder()
                .id(++TodoLastId)
                .body(body)
                .build();

        todos.add(todo);

        return todo;
    }

    @GetMapping("/remove/{id}")
    public boolean remove(
            @PathVariable long id
    ){
       boolean removed=todos.removeIf(todo -> todo.getId()==id);

       return removed;
    }

    @GetMapping("/modify/{id}")
    public boolean modify(
            @PathVariable long id,
            String body
    ){
        Todo todo=todos
                    .stream().
                    filter(
                            _todo -> _todo.getId()==id
                    )
                    .findFirst()
                    .orElse(null);

        if (todo==null) return false;

        todo.setBody(body);

        return true;
    }
}
