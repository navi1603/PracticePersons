package by.warlock.services;

import by.warlock.models.Person;
import by.warlock.utils.DoubleUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class AppMenu {
    BufferedReader br;
    private final String ageRegExp = "^\\d{1,3}$";
    private final String passportRegExp = "^[A-Z]{2}\\d{6}$";
    PersonsAccounting personsAccounting;

    public AppMenu() {
        personsAccounting = new PersonsAccounting();
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public void start() throws IOException {
        help();
        while (true) {
            String key = br.readLine().toLowerCase().strip();
            switch (key) {
                case "in" -> add();
                case "del" -> delete();
                case "count" -> count();
                case "avg" -> avgAge();
                case "median" -> medianAge();
                case "young" -> young();
                case "old" -> old();
                case "print" -> printAll();
                case "help" -> help();
                case "exit" -> {
                    exit();
                    return;
                }
                default -> System.out.println("Неизвестная команда. Попробуйте снова");
            }
        }
    }

    private void add() throws IOException {
        System.out.println("Добавление нового пользователя.\nВведите номер паспорта: ");
        String passportNumber;
        Person newPerson = null;

        while (true) {
            passportNumber = br.readLine().toUpperCase().strip();
            if (checkPassportNumberFormat(passportNumber)) {
                if (Objects.isNull(newPerson)) {
                    newPerson = new Person(passportNumber);
                }
                if (!personsAccounting.containsPerson(newPerson)) {
                    System.out.println("Введите имя: ");
                    newPerson.setName(br.readLine());

                    System.out.println("Введите возраст: ");
                    String age;
                    while (true) {
                        age = br.readLine().strip();
                        if (checkAgeFormat(age)) {
                            newPerson.setAge(Integer.parseInt(age));
                            personsAccounting.addPerson(newPerson);
                            System.out.println("Пользователь успешно добавлен!");
                            break;
                        }
                    }
                } else {
                    System.out.println("Пользователь с таким номером паспорта уже внесен.");
                }
                break;
            }
        }
    }

    private void delete() throws IOException {
        System.out.println("Удаление пользователя.\nВведите номер паспорта: ");
        String passportNumber = br.readLine();
        if (personsAccounting.deletePerson(passportNumber)) {
            System.out.printf("Пользователь c номером паспорта %s успешно удален!%n", passportNumber);
        } else {
            System.out.println("Пользователь с указанным паспортом не найден");
        }
    }

    private void count() {
        System.out.printf("Количество пользователей: %d. %n", personsAccounting.getCountPersons());
    }

    private void avgAge() {
        if (DoubleUtils.equals(personsAccounting.getAvgAgePersons(), 0.0)) {
            System.out.println("Ни один пользователь не введен.");
        } else {
            System.out.printf("Средний возраст: %2.1f%n", personsAccounting.getAvgAgePersons());
        }
    }

    private void medianAge() {
        if (DoubleUtils.equals(personsAccounting.getMedianAgePersons(), 0.0)) {
            System.out.println("Ни один пользователь не введен.");
        } else {
            System.out.printf("Медиана возраста: %2.1f%n", personsAccounting.getMedianAgePersons());
        }
    }

    private void young() {
        System.out.println("Cамый молодой пользователь:");
        System.out.println(personsAccounting.getYoungPerson());
    }

    private void old() {
        System.out.println("Cамый старший пользователь:");
        System.out.println(personsAccounting.getOldPerson());
    }

    private void printAll() {
        System.out.println(personsAccounting);
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

    private boolean checkPassportNumberFormat(String passportNumber) {
        boolean respond = passportNumber.matches(passportRegExp);
        if (!respond) {
            String message = """
                    Неверный формат. Номер содержит 2 букв латинского алфавита и 6 цифр.
                    Например, BY009754. Попробуйте еще раз...""";
            System.out.println(message);
        }
        return respond;
    }

    private boolean checkAgeFormat(String personAge) {
        boolean respond = personAge.matches(ageRegExp);
        if (!respond) {
            String message = """
                    Неверный формат. Укажите возраст цифрами не более 3.
                    Например, 29. Попробуйте еще раз...""";
            System.out.println(message);
        }
        return respond;
    }
}
