package by.warlock.services;

import by.warlock.models.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AppMenu {
    Set<Person> persons;
    BufferedReader br;
    private String regExp = "^\\d{3,}$";

    public AppMenu() {
        br = new BufferedReader(new InputStreamReader(System.in));
        persons = new HashSet<>(Set.of(
                new Person("Иван", 23, "MP11111111"),
                new Person("Александр", 20, "MP22222222"),
                new Person("Сергей", 32, "MP333333333"),
                new Person("Василий", 40, "MP44444444"),
                new Person("Степан", 47, "MP55555555"),
                new Person("Светлана", 17, "MP66666666"))
        );
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
        System.out.printf("Количество пользователей: %d. %n", persons.size());
    }

    //O(n)
    //Программа рассчитывает и выводит средний возраст всех пользователей.
    //В консоль выводится вычисленное значение
    //Если не добавлено ни одного пользователя, выводится сообщение.
    private void avgAgePersons() {
        int sumAge = 0;
        if (!persons.isEmpty()) {
            Iterator<Person> personIterator = persons.iterator();
            while (personIterator.hasNext()) {
                Person person = personIterator.next();
                sumAge += person.getAge();
            }
        } else {
            System.out.println("Ни один пользователь не введен.");
            return;
        }
        System.out.printf("Средний возраст: %2.1f%n", (double) sumAge / persons.size());
    }

    //O(n)
    /*Программа рассчитывает и выводит медиану возраста всех пользователей.
    В консоль выводится вычисленное значение:
    Если не добавлено ни одного пользователя, выводится сообщение.*/
    private void medianAgePersons() {
        double ageMedian = 0;
        if (!persons.isEmpty()) {
            List<Person> list = new ArrayList<>(persons);
            Collections.sort(list);
            if(list.size() % 2 == 0) {
                ageMedian = (double)(list.get((list.size() / 2 - 1).getAge() + list.get(list.size()  / 2 + 1).getAge())) / 2;
            } else {
                ageMedian = list.get(list.size() / 2).getAge();
            }
        } else {
            System.out.println("Ни один пользователь не введен.");
            return;
        }
        System.out.printf("Медиана возраста: %2.1f%n", ageMedian );
    }

    //O(log n)
    //Выводит самого молодого пользователя.
    private void youngPerson() {
        System.out.println("Cамый молодой пользователь:");
        TreeSet<Person> sortedPersons = new TreeSet<>(persons);
        System.out.println(sortedPersons.getFirst().toString());

    }

    //O(log n)
    //Выводит самого старшего пользователя.
    private void oldPerson() {
        System.out.println("Cамый старший пользователь:");
        TreeSet<Person> sortedPersons = new TreeSet<>(persons);
        System.out.println(sortedPersons.getLast().toString());
    }

    //O(n)
    //Построчно выводит всех пользователей, отсортированных по возрасту от младшего к старшему.
    private void printAllPersons() {
        System.out.println("Список всех пользователей:");
        TreeSet<Person> sortedPersons = new TreeSet<>(persons);
        for (Person person : sortedPersons) {
            System.out.println(person.toString());
        }
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
