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
        System.out.println("Средний возраст: ");
    }

    private void median(){
        System.out.println("Медиана возраста: ");
    }

    private void young(){

    }

    private void old(){

    }

    private void print(){
        System.out.println("Список всех пользователей:");

    }

    private void help(){
        String helpMessage = """
                Список доступных комманд:
                
                in - добавить нового пользователя.
                del - удалить пользователя по номеру паспорта.
                count - количество добавленных пользователей.
                avg - вывести средний возраст пользователей.
                median - вывести медиану возраста всех пользователей.
                young - вывести самого молодого пользователя.
                old - вывести самого старшего пользователя.
                print - вывести всех пользователей, отсортированных по возрасту.
                help - отобразить справку.
                exit - выйти из приложения.
                """;
        System.out.println(helpMessage);
    }

    private void exit(){
        System.out.println("Программа завершена");
        System.exit(0);
    }

}
