package controller;

import model.User;

public class Manager {
    private static User player = null;

    public static void setPlayer(User player) {
        Manager.player = null;
        Manager.player = player;
    }
    public static int getLevel(){
        return player.getLastLevel();
    }
    public static String username(){
        return player.userName;
    }
    public static boolean loggedIn(){
        return player!=null;
    }
    public static void logout(){
        player = null;
    }
}
