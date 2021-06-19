package controller;

import model.User;

public class Manager {
    private static User player;

    public static void setPlayer(User player) {
        Manager.player = null;
        Manager.player = player;
    }
    public static int getLevel(){
        return player.getLastLevel();
    }
}
