package by.warlock.models;

import java.util.Comparator;

public class Person implements Comparator<Person> {
    private String name;
    private int age;
    private String passportNumber;

    public Person(String name, int age, String passportNumber) {
        this.name = name;
        this.age = age;
        this.passportNumber = passportNumber;
    }

    @Override
    public int compare(Person o1, Person o2) {
        if(o1.age > o2.age) return 1;
        if(o1.age < o2.age) return -1;
        return 0;
    }
}
