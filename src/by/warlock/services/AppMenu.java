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

    /* O(1)
    Приложение просит ввести номер паспорта, имя и возраст пользователя и сохраняет данные в память.
    Если пользователь с таким номером паспорта уже существует, выводится соответствующее сообщение и данные пользователя в памяти не обновляются.
    Проверка на наличие пользователя по паспорту выполняется сразу, до ввода имени и возраста.
    Возраст должен быть целым неотрицательным числом.
    В случае успешного добавления данных пользователя выводится сообщение.*/
    private void add() throws IOException {
        System.out.println("Добавление нового пользователя.\nВведите номер паспорта: ");
        String passportNumber = br.readLine().toUpperCase().strip();
        Person newPerson = null;

        if (checkPassportNumberFormat(passportNumber)) {
            newPerson = new Person(passportNumber);
            if (Objects.nonNull(newPerson) && !personsAccounting.containsPerson(newPerson)) {
                System.out.println("Введите имя: ");
                newPerson.setName(br.readLine());

                System.out.println("Введите возраст: ");
                String age = br.readLine().strip();
                if (checkAgeFormat(age)) {
                    newPerson.setAge(Integer.parseInt(age));
                    personsAccounting.addPerson(newPerson);
                    System.out.println("Пользователь успешно добавлен!");
                }
            } else {
                System.out.println("Пользователь с таким номером паспорта уже внесен.");
            }
        }
    }

    /*O(1)
    Приложение запрашивает номер паспорта и удаляет пользователя с таким паспортом.
    Если пользователь удалён, выводится сообщение.
    Если пользователь с указанным паспортом не найден, выводится сообщение.*/
    private void delete() throws IOException {
        System.out.println("Удаление пользователя.\nВведите номер паспорта: ");
        String passportNumber = br.readLine();
        if (personsAccounting.deletePerson(passportNumber)) {
            System.out.printf("Пользователь c номером паспорта %s успешно удален!%n", passportNumber);
        } else {
            System.out.println("Пользователь с указанным паспортом не найден");
        }
    }

    //O(1)
    //Выводит количество пользователей в памяти.
    private void count() {
        System.out.printf("Количество пользователей: %d. %n", personsAccounting.getCountPersons());
    }

    //O(n)
    //Программа рассчитывает и выводит средний возраст всех пользователей.
    //В консоль выводится вычисленное значение
    //Если не добавлено ни одного пользователя, выводится сообщение.
    private void avgAge() {
        if(DoubleUtils.equals(personsAccounting.getAvgAgePersons(), 0.0)) {
            System.out.println("Ни один пользователь не введен.");
        } else {
            System.out.printf("Средний возраст: %2.1f%n", personsAccounting.getAvgAgePersons());
        }
    }

    /*O(n)
    Программа рассчитывает и выводит медиану возраста всех пользователей.
    В консоль выводится вычисленное значение:
    Если не добавлено ни одного пользователя, выводится сообщение.*/
    private void medianAge() {
        if(DoubleUtils.equals(personsAccounting.getMedianAgePersons(), 0.0)) {
            System.out.println("Ни один пользователь не введен.");
        } else {
            System.out.printf("Медиана возраста: %2.1f%n", personsAccounting.getMedianAgePersons());
        }
    }

    //O(log n)
    //Выводит самого молодого пользователя.
    private void young() {
        System.out.println("Cамый молодой пользователь:");
        System.out.println(personsAccounting.getYoungPerson());
    }

    //O(log n)
    //Выводит самого старшего пользователя.
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
    private boolean checkPassportNumberFormat(String passportNumber){
        boolean respond = passportNumber.matches(passportRegExp);
        if (!respond) {
            System.out.println("Неверный формат номера паспорта.\n" +
                    "Номер содержит 2 букв латинского алфавита и 6 цифр.\n" +
                    "Например, BY009754.");
            respond = false;
        }
        return respond;
    }

    private boolean checkAgeFormat(String personAge){
        boolean respond = personAge.matches(ageRegExp);
        if (!respond) {
            System.out.println("Неверный формат возраста.\n" +
                    "Укажите возраст цифрами не более 3.\n" +
                    "Например, 29.");
            respond = false;
        }
        return respond;
    }
}
