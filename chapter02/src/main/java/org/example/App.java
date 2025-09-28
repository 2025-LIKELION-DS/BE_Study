package org.example;


import java.util.Scanner;

public class App {

    private  Scanner scanner;
    private org.example.TodoController todoController;
    private SystemController systemController;

    public App(){
        todoController= new org.example.TodoController();
        systemController = new SystemController();
        scanner=new Scanner(System.in);
    }

    public void run() {

        while (true){
            System.out.print("명령) ");
            String cmd=scanner.nextLine().trim();

            if (cmd.equals("exit")){
                systemController.exit();
                break;}
            else if (cmd.equals("add")){
                todoController.add();
            }
            else if (cmd.equals("list")){
                todoController.list();
            }
            else if (cmd.equals("del")){
                todoController.delete();
            }
            else if (cmd.equals("modify")){
                todoController.modify();

            }
        }


        System.out.println("할일 관리 끝");
    }


}