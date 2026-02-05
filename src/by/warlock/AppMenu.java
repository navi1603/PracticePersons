package by.warlock;

import by.warlock.models.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AppMenu {
    List<Person> persons;
    BufferedReader br;

    public AppMenu() {
        br = new BufferedReader(new InputStreamReader(System.in));
        persons = new ArrayList<>();

    }

    public void start() throws IOException {
        while(true) {
            String key = br.readLine().toLowerCase().strip();

            switch (key) {
                case "in" -> in();
                case "del" -> del();
                case "count" -> count();
                case "avg" -> avg();
                case "median" -> median();
                case "young" -> young();
                case "old" -> old();
                case "print" -> print();
                case "help" -> help();
                case "exit" -> {
                    exit();
                    return;
                }
                default -> System.out.println("Неизвестная команда. Попробуйте снова");

            }
        }
    }

    private void in() {

    }

    private void del() {

    }
    private void count() {

    }

    private void avg() {

    }

    private void median(){

    }

    private void young(){

    }

    private void old(){

    }

    private void print(){

    }

    private void help(){
        String helpMessage = """
                Список доступных комманд:
                in - 
                del -
                count -
                avg -
                median -
                young -
                old -
                print -
                help -
                exit -
                """;
        System.out.println();
    }

    private void exit(){
        System.out.println("Программа завершена");
        System.exit(0);
    }

}
