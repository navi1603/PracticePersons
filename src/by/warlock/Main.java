package by.warlock;

import by.warlock.services.AppMenu;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        AppMenu appMenu = new AppMenu();
        appMenu.start();
    }
}
