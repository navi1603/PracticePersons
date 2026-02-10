package by.warlock.services;

import by.warlock.models.Person;

import java.util.*;

public class PersonsAccounting {
    private Set<Person> persons;
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

    public boolean containsPerson(Person person) {
        return persons.contains(person);
    }

    public boolean addPerson(Person person) {
        return persons.add(person);
    }

    public boolean deletePerson(String passportNumber) {
        Person person = new Person(passportNumber);
        return persons.remove(person);
    }

    public int getCountPersons() {
        return persons.size();
    }

    public double getAvgAgePersons() {
        int sumAge = 0;
        if (!persons.isEmpty()) {
            for (Person person : persons) {
                sumAge += person.getAge();
            }
        }
        return (double) sumAge / persons.size();
    }

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

    public String getYoungPerson() {
        sortedPersons = new TreeSet<>(persons);
        return sortedPersons.getFirst().toString();
    }

    public String getOldPerson() {
        sortedPersons = new TreeSet<>(persons);
        return sortedPersons.getLast().toString();
    }

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
