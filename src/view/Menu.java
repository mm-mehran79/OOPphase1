package view;

import java.util.HashMap;
import java.util.Scanner;

public abstract class Menu {
    protected String name;
    protected Menu parentMenu;
    protected HashMap<Integer, Menu> submenus;
    public static Scanner scanner;

    public Menu(String name, Menu parentMenu) {
        this.name = name;
        this.parentMenu = parentMenu;
    }

    public Menu(String name) {
        this.name = name;
    }

    public Menu(Menu parentMenu) {
        this.parentMenu = parentMenu;
    }

    public void show() {
        System.out.println(this.name + ": ");
        for (Integer integer : submenus.keySet()) {
            System.out.println(integer + ". " + submenus.get(integer).getName());
        }
        if (this.parentMenu == null) {
            System.out.println((submenus.size() + 1) + ". exit");
        } else {
            System.out.println((submenus.size() + 1) + ". back");
        }
    }

    public void execute() {
        Menu nextMenu;
        while (true) {
            int nextMenuNum = Integer.parseInt(scanner.nextLine());
            if (nextMenuNum == submenus.size() + 1) {
                if (this.parentMenu == null) {
                    System.exit(1);
                } else {
                    nextMenu = parentMenu;
                    break;
                }
            } else if (nextMenuNum < submenus.size() + 1 && nextMenuNum > 0) {
                nextMenu = submenus.get(nextMenuNum);
                break;
            } else {
                System.err.println("Invalid input!");
            }
        }
        nextMenu.show();
        nextMenu.execute();
    }

    public void setSubmenus(HashMap<Integer, Menu> submenus) {
        this.submenus = submenus;
    }

    public static void setScanner(Scanner scanner) {
        Menu.scanner = scanner;
    }

    public String getName() {
        return name;
    }
}
