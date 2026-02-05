package by.warlock.models;

public class Person implements Comparable<Person> {
    private String name;
    private int age;
    private String passportNumber;

    public Person(String name, int age, String passportNumber) {
        this.name = name;
        this.passportNumber = passportNumber;
        setAge(age);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age > 0) {
            this.age = age;
        } else {
            System.out.println("Возраст не может быть отрицательным числом или 0.");
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", passportNumber='" + passportNumber + '\'' +
                '}';
    }

    @Override
    public int compareTo(Person o1) {
        if(this.age > o1.age) return 1;
        else if(this.age < o1.age) return -1;
        else return 0;
    }
}
