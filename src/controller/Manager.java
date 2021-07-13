package controller;

import log.Log;
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
        try{
            return player.userName;
        }
        catch (NullPointerException e){
            System.err.println("please login first");
            Log.log(Log.ERROR,"need username before logging in");
            return null;
        }
    }
    public static int getCoins(){
        return player.getCoins();
    }
    public static boolean loggedIn(){
        return player!=null;
    }
    public static void logout(){
        player = null;
    }
}
