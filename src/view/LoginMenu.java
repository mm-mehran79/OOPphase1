package view;

import controller.LevelManager;
import controller.Manager;
import log.Log;
import model.User;

public class LoginMenu extends Menu{
    private User user;
    public LoginMenu(Menu parentMenu) {
        super("Login",parentMenu);
    }
    @Override
    public void show(){
        System.out.println(name + " Menu");
        String username,pass;
        System.out.println("Enter your username: ");
        while (!User.available(username = scanner.nextLine())){
            if(username.equalsIgnoreCase("back")) {parentMenu.show();parentMenu.execute();}
            System.out.println("Username is invalid. Enter another username or go \"back\"");
        }
        System.out.println("Ok, now enter password");
        while ((user = User.loadUser(username,(pass = scanner.nextLine()))) == null){
            if(pass.equalsIgnoreCase("back")) {parentMenu.show();parentMenu.execute();}
            System.out.println("Password is incorrect. enter correct password:");
        }
    }

    @Override
    public void execute(){
        Manager.setPlayer(user);
        Log.log(Log.LOG, user.userName + " signed in");
        int n;
        while (true){
            while (true){
                System.out.println("please enter valid level. Max = "+Manager.getLevel());
                n = Integer.parseInt(scanner.nextLine());
                if(n<=Manager.getLevel() && n > 0){
                    LevelManager levelManager = new LevelManager(n,Manager.getCoins());
                    LevelInputProcessor levelInputProcessor = new LevelInputProcessor(levelManager, scanner);
                    int k = levelInputProcessor.run();
                    if (k >= 0){
                        if( n != user.getLastLevel())
                            user.setLastLevel(user.getLastLevel() - 1);
                        user.giveReward(k);
                        User.saveUser(user);
                        System.out.println("congratulation!!! You passed the level ");
                    }
                    break;
                }
            }
            System.out.println("do you want to continue?...\n1. Yes\n2. No");
            n = Integer.parseInt(scanner.nextLine());
            if(n!=1)
                parentMenu.show();
        }

    }
}
