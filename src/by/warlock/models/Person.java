package by.warlock.models;

import java.util.Objects;

public class Person implements Comparable<Person> {
    private String passportNumber;
    private String name;
    private int age;

    public Person() {
    }

    public Person(String passportNumber) {
        setPassportNumber(passportNumber);
    }

    public Person(String name, int age, String passportNumber) {
        this(passportNumber);
        this.name = name;
        setAge(age);
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber.toUpperCase().strip();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        } else {
            System.out.println("Возраст не может быть отрицательным числом или 0.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(passportNumber, person.passportNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(passportNumber) * 36;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String message = String.format("User name is %s, %d years old. Passport number is %s.%n", name, age, passportNumber);
        sb.append(message);
        return sb.toString();
    }

    @Override
    public int compareTo(Person o2) {
        if(this.getAge() > o2.getAge()) return 1;
        else if(this.getAge() < o2.getAge()) return -1;
        return 0;
    }
}
