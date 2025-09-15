package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    private List<Todo> todos = new ArrayList<>(); //할일 리스트 - 전역
    private long todosLastId = 0;
    Scanner scanner=new Scanner(System.in);

    public void run(){
        System.out.println("할 일 관리 앱 시작!");
            while (true){
                System.out.println("명령)");
                String cmd=scanner.nextLine().trim();

                if (cmd.equals("exit")) break;
                else if (cmd.equals("add")) {
                    add(); // 메서드 분리
                } else if (cmd.equals("list")) {
                    System.out.println("번호 / 내용");

                    todos.forEach(todo -> System.out.printf("%d / %s\n", todo.getId(), todo.getContent()));
                } else if (cmd.equals("del")) {
                    System.out.print("삭제할 할일번호 : ");
                    long id = Long.parseLong(scanner.nextLine().trim());

                    // 리스트.removeIf(요소->조건식) : 조건이 참인 거 모두 제거
                    boolean isRemoved=todos.removeIf(todo->todo.getId()==id);

                    if (isRemoved) {
                        System.out.printf("%d번 할일이 삭제되었음.\n", id);
                    } else {
                        System.out.printf("%d번 할일은 존재하지 않음.\n", id);
                    }

                    todos.forEach(todo -> System.out.printf("%d / %s\n", todo.getId(), todo.getContent()));
                } else if (cmd.equals("modify")) {
                    System.out.print("수정할 할일 번호 : ");
                    long id = Long.parseLong(scanner.nextLine().trim());

                    Todo foundTodo = todos.stream()
                            .filter(t -> t.getId() == id)
                            .findFirst()
                            .orElse(null);

                    if (foundTodo == null) {
                        System.out.printf("%d번 할 일은 존재하지 않음.\n", id);
                        continue;
                    }

                    System.out.printf("기존 할 일 : %s\n", foundTodo.getContent());
                    System.out.print("새 할 일 : ");
                    String newContent = scanner.nextLine().trim();
                    foundTodo.setContent(newContent);

                    System.out.printf("%d번 할 일이 수정됨.\n", id);
                }

                //System.out.println("입력한 명령 : %s\n"+cmd);
            }

        //scanner.close();

        System.out.println("할 일 관리 앱 끝!");
    }

    private void add() {
        long id = todosLastId + 1;
        System.out.print("할일 : ");
        String content = scanner.nextLine().trim();

        Todo todo = new Todo(id, content);
        todos.add(todo);
        todosLastId++;

        System.out.printf("%d번 할일 생성\n", id);
    }
}
