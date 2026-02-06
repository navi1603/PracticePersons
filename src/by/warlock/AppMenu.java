package by.warlock;

import by.warlock.models.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class AppMenu {
    Set<Person> persons;
    BufferedReader br;
    private String regExp = "^\\d{3,}$";
    ;

    public AppMenu() {
        br = new BufferedReader(new InputStreamReader(System.in));
        persons = new HashSet<>();
    }

    public void start() throws IOException {
        help();
        while (true) {
            String key = br.readLine().toLowerCase().strip();

            switch (key) {
                case "in" -> addPerson();
                case "del" -> deletePerson();
                case "count" -> countPersons();
                case "avg" -> avgAgePersons();
                case "median" -> medianAgePersons();
                case "young" -> youngPerson();
                case "old" -> oldPerson();
                case "print" -> printAllPersons();
                case "help" -> help();
                case "exit" -> {
                    exit();
                    return;
                }
                default -> System.out.println("Неизвестная команда. Попробуйте снова");

            }
        }
    }

    /*
    Приложение просит ввести номер паспорта, имя и возраст пользователя и сохраняет данные в память.
    Если пользователь с таким номером паспорта уже существует, выводится соответствующее сообщение и данные пользователя в памяти не обновляются.
    Проверка на наличие пользователя по паспорту выполняется сразу, до ввода имени и возраста.
    Возраст должен быть целым неотрицательным числом.
    В случае успешного добавления данных пользователя выводится сообщение.*/
    //O(1)
    private void addPerson() throws IOException {
        System.out.println("Добавление нового пользователя.\nВведите номер паспорта: ");
        String passportNumber = br.readLine();
        Person newPerson = new Person(passportNumber);
        if (!persons.contains(newPerson)) {
            System.out.println("Введите имя: ");
            newPerson.setName(br.readLine());

            System.out.println("Введите возраст: ");
            if (br.readLine().matches(regExp)) {
                newPerson.setAge(Integer.parseInt(br.readLine()));
            }
            persons.add(newPerson);
            System.out.println("Пользователь успешно добавлен!");

        } else {
            System.out.println("Пользователь с таким номером паспорта уже внесен.");
        }

    }

    //O(1)
    /*Приложение запрашивает номер паспорта и удаляет пользователя с таким паспортом.
    Если пользователь удалён, выводится сообщение.
    Если пользователь с указанным паспортом не найден, выводится сообщение.*/
    private void deletePerson() throws IOException {
        System.out.println("Удаление пользователя.\nВведите номер паспорта: ");
        String passportNumber = br.readLine();
        Person newPerson = new Person(passportNumber);

        if (persons.remove(newPerson)) {
            System.out.printf("Пользователь c номером паспорта %s успешно удален!%n", newPerson.getPassportNumber());
        } else {
            System.out.println("Пользователь с указанным паспортом не найден");
        }

    }

    //O(1)
    //Выводит количество пользователей в памяти.
    private void countPersons() {
        System.out.printf("Количество пользователей %d. %n", persons.size());
    }

    //O(n)
    //Программа рассчитывает и выводит средний возраст всех пользователей.
    //В консоль выводится вычисленное значение
    //Если не добавлено ни одного пользователя, выводится сообщение.
    private void avgAgePersons() {
        System.out.println("Средний возраст: %2d ");
    }

    //O(n)
    /*Программа рассчитывает и выводит медиану возраста всех пользователей.
    В консоль выводится вычисленное значение:
    Если не добавлено ни одного пользователя, выводится сообщение.*/
    private void medianAgePersons() {
        System.out.println("Медиана возраста: ");
    }

    //O(log n)
    //Выводит самого молодого пользователя.
    private void youngPerson() {

    }

    //O(log n)
    //Выводит самого старшего пользователя.
    private void oldPerson() {

    }

    //O(n)
    //Построчно выводит всех пользователей, отсортированных по возрасту от младшего к старшему.
    private void printAllPersons() {
        System.out.println("Список всех пользователей:");

    }

    private void help() {
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

    private void exit() {
        System.out.println("Программа завершена");
        System.exit(0);
    }

}
