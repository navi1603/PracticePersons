package by.warlock;

import by.warlock.models.Person;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class AppMenu {
    Deque<Person> persons;
    BufferedReader br;

    public AppMenu() {
        br = new BufferedReader(new InputStreamReader(System.in));
        persons = new ArrayDeque<>();

    }

    public void start() {
        persons.getFirst();
    }
}
