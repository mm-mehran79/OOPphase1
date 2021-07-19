package view;

import controller.LevelManager;
import controller.Manager;
import log.Log;
import model.User;

public class SignUp extends Menu{
    private User user;


    public SignUp(Menu parentMenu){
        super("Sign up",parentMenu);
    }
    @Override
    public void show(){
        System.out.println(name+" menu:");
        String userName,pass ;
        System.out.println("Enter new UserName:");
        while (User.available(userName = scanner.nextLine())){
            if(userName.equalsIgnoreCase("back")) {parentMenu.show();parentMenu.execute();}
            System.out.println("the username is already taken. Enter another username or go \"back\"");
        }
        System.out.println("done\nEnter your password");
        pass = scanner.nextLine();
        user = new User(userName, pass);
        User.saveUser(user);
        Log.log(Log.LOG,"user " + userName + "added & saved");
    }
    @Override
    public void execute(){
        Manager.setPlayer(user);
        Log.log(Log.LOG, user.userName + " signed in");
        LevelManager levelManager = new LevelManager(Manager.getLevel(),Manager.getCoins());
        LevelInputProcessor levelInputProcessor = new LevelInputProcessor(levelManager, scanner);
        int k = levelInputProcessor.run();
        if (k >= 0){
            user.giveReward(k);
            User.saveUser(user);
            System.out.println("congratulation!!! You passed 1st level ");
        }
        System.out.println("do you want to continue?...\n1. Yes\n2. No");
        if(Integer.parseInt(scanner.nextLine())  != 1)
            parentMenu.show();
        {
            while (true){
                int n;
                while (true){
                    System.out.println("please enter valid level. Max = "+Manager.getLevel());
                    n = Integer.parseInt(scanner.nextLine());
                    if(n<=Manager.getLevel() && n > 0){
                        levelManager = new LevelManager(n,Manager.getCoins());
                        levelInputProcessor = new LevelInputProcessor(levelManager, scanner);
                        k = levelInputProcessor.run();
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
}
