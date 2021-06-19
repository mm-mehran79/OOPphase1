package view;

import controller.Manager;

public class GameMenu extends Menu{
    public GameMenu(Menu parentMenu){
        super("Game Menu",parentMenu);
        submenus.put(1,new StartMenu(this));
        submenus.put(2,new SettingMenu(this));
    }

    @Override
    public void show() {
        System.out.println(this.name + ": ");
        for (Integer integer : submenus.keySet()) {
            System.out.print(integer + ". " + submenus.get(integer).getName());
            if(integer == 0) System.out.println(Manager.getLevel()+1);
            else System.out.println();
        }
        System.out.println((submenus.size() + 1) + ". logout");
    }
    @Override
    public void execute() {
        Menu nextMenu;
        while (true) {
            String s = scanner.nextLine();
            if (InputCommand.Exit.getMatcher(s).matches())
                System.exit(1);
            int nextMenuNum = Integer.parseInt(s);
            if (nextMenuNum == submenus.size() + 1) {
                nextMenu = parentMenu;
                break;
            } else if (nextMenuNum < submenus.size() + 1 && nextMenuNum > 0) {
                nextMenu = submenus.get(nextMenuNum);
                Manager.logout();
                break;
            } else {
                System.err.println("Invalid input! \nEnter correct number");
            }
        }
        nextMenu.show();
        nextMenu.execute();
    }
}
