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
        this.submenus = new HashMap<>();
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
            String s = scanner.nextLine();
            if (InputCommand.Exit.getMatcher(s).matches())
                System.exit(1);
            int nextMenuNum;
            try {
                nextMenuNum = Integer.parseInt(s);
            }
            catch (NumberFormatException e){
                nextMenuNum = submenus.size() + 1;
            }
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
                System.err.println("Invalid input!\nEnter correct number:");
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
