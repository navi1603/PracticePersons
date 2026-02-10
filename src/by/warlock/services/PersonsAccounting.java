package by.warlock.services;

import by.warlock.models.Person;

import java.util.*;

public class PersonsAccounting {
    Set<Person> persons;
    private TreeSet<Person> sortedPersons;

    public PersonsAccounting() {
        persons = new HashSet<>(Set.of(
                new Person("Иван", 23, "MP111111"),
                new Person("Александр", 20, "MP222222"),
                new Person("Сергей", 32, "MP333333"),
                new Person("Василий", 40, "MP444444"),
                new Person("Степан", 47, "MP555555"),
                new Person("Светлана", 17, "MP666666"))
        );
    }

    public boolean containsPerson(Person person){
        return persons.contains(person);
    }

    /*O(1)
   Приложение просит ввести номер паспорта, имя и возраст пользователя и сохраняет данные в память.
   Если пользователь с таким номером паспорта уже существует, выводится соответствующее сообщение и данные пользователя в памяти не обновляются.
   Проверка на наличие пользователя по паспорту выполняется сразу, до ввода имени и возраста.
   Возраст должен быть целым неотрицательным числом.
   В случае успешного добавления данных пользователя выводится сообщение.
   */
    public boolean addPerson(Person person) {
        return persons.add(person);
    }

    //O(1)
    public boolean deletePerson(String passportNumber) {
        Person person = new Person(passportNumber);
        return persons.remove(person);
    }

    //O(1)
    //Выводит количество пользователей в памяти.
    public int getCountPersons() {
        return persons.size();
    }

    //O(n)
    //Программа рассчитывает и выводит средний возраст всех пользователей.
    //В консоль выводится вычисленное значение
    //Если не добавлено ни одного пользователя, выводится сообщение.
    public double getAvgAgePersons() {
        int sumAge = 0;
        if (!persons.isEmpty()) {
            for (Person person : persons) {
                sumAge += person.getAge();
            }
        }
        return (double) sumAge / persons.size();
    }

    //O(n)
    /*Программа рассчитывает и выводит медиану возраста всех пользователей.
    В консоль выводится вычисленное значение:
    Если не добавлено ни одного пользователя, выводится сообщение.*/
    public double getMedianAgePersons() {
        double ageMedian = 0;
        if (!persons.isEmpty()) {
            List<Person> list = new ArrayList<>(persons);
            Collections.sort(list);
            int avgIndex = list.size() / 2;
            if (list.size() % 2 == 0) {
                ageMedian = (double) (list.get(avgIndex - 1).getAge() + list.get(avgIndex).getAge()) / 2;
            } else {
                ageMedian = list.get(avgIndex).getAge();
            }
        }
        return ageMedian;
    }

    //O(log n)
    //Выводит самого молодого пользователя.
    public String getYoungPerson() {
        sortedPersons = new TreeSet<>(persons);
        return sortedPersons.getFirst().toString();
    }

    //O(log n)
    //Выводит самого старшего пользователя.
    public String getOldPerson() {
        sortedPersons = new TreeSet<>(persons);
        return sortedPersons.getLast().toString();
    }

    //O(n)
    //Построчно выводит всех пользователей, отсортированных по возрасту от младшего к старшему.
    @Override
    public String toString() {
        StringBuilder personsList = new StringBuilder();
        personsList.append("Список всех пользователей:\n");
        sortedPersons = new TreeSet<>(persons);
        for (Person person : sortedPersons) {
            personsList.append(person.toString());
        }
        return personsList.toString();
    }
}
