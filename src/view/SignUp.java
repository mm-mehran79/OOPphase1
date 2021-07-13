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
        System.out.println(levelInputProcessor.run());
    }
}
