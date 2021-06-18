package view;

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
        while ((user = User.loadUser(username,pass = scanner.nextLine())).equals(null)){
            System.out.println("Password is incorrect. enter correct password:");
        }
    }

    @Override
    public void execute(){
        Manager.setPlayer(user);
        Log.log(Log.LOG, user.userName + " signed in");
        //TODO: JUST SPECIFY TO RUN GAME MENU
    }
}