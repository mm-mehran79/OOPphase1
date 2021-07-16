package model;

import log.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class User {
    public String userName;
    private String password;
    private int coins,lastLevel;
    private static File usersFile;
    static {
        usersFile =new File("Users.txt");
        try {
            if (usersFile.createNewFile()){
                Log.log(Log.INFO,"Users file created");
            }
        } catch (IOException e) {
            Log.log(e);
            e.printStackTrace();
        }
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.coins = 0;
        this.lastLevel = 1;
    }

    public User(String userName, String password, int coins, int lastLevel) {
        this.userName = userName;
        this.password = password;
        this.coins = coins;
        this.lastLevel = lastLevel;
    }

    public int getLastLevel() {
        return lastLevel;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setLastLevel(int lastLevel) {
        this.lastLevel = lastLevel;
    }

    public static void saveUser(User user){//save user to users file
        if(available(user.userName)){
            try {
                File tempFile = new File("tempUsers.txt");
                FileWriter tempWriter =new FileWriter(tempFile,true);
                tempWriter.flush();
                Scanner userScanner = new Scanner(usersFile);
                String s;
                while (userScanner.hasNextLine()){
                    s = userScanner.nextLine();
                    if(s.split("#")[0].equalsIgnoreCase(user.userName)){
                        tempWriter.write(user.userName+"#"+user.password+"#");
                        tempWriter.write(user.coins+"#"+ user.lastLevel+"\n");
                    }
                    else
                        tempWriter.write(s+"\n");
                }
                tempWriter.close();
                usersFile.delete();
                tempFile.renameTo(usersFile);
                usersFile = tempFile;
                Log.log(Log.INFO,"users file updated successfully");
            }
            catch (IOException e){
                Log.log(Log.EXCEPTION,"In saving Users: "+ e);
                e.printStackTrace();
            }

        }
        else try {
            FileWriter fileWriter = new FileWriter(usersFile,true);
            fileWriter.write(user.userName+"#"+user.password+"#");
            fileWriter.write(user.coins+"#"+ user.lastLevel+"\n");
            fileWriter.close();
        } catch (IOException e) {
            Log.log(Log.EXCEPTION,"In saving users:"+e.toString());
            e.printStackTrace();
        }
    }
    public static User loadUser(String userName,String password){
        try {
            Scanner userScanner = new Scanner(usersFile);
            String s;
            while (userScanner.hasNextLine()){
                s = userScanner.nextLine();
                if(s.split("#")[0].equalsIgnoreCase(userName))
                    if(s.split("#")[1].equals(password))
                    return new User(userName,password,Integer.parseInt(s.split("#")[2]),Integer.parseInt(s.split("#")[3]));
                    else return null;// if pass was wrong
            }
            return null;// never happen
        } catch (FileNotFoundException e) {
            Log.log(Log.EXCEPTION,e.toString()+"& unable to load users");
            makeUsersFile();
            return loadUser(userName,password);
        }
    }
    public static boolean available(String userName){//check the username is repetitious or not
        try {
            Scanner userScanner = new Scanner(usersFile);
            while (userScanner.hasNextLine())
                if(userScanner.nextLine().split("#")[0].equalsIgnoreCase(userName))
                    return true;
            return false;
        } catch (FileNotFoundException e) {
            Log.log(Log.EXCEPTION,e.toString()+"& unable to load users");
            makeUsersFile();
            return false;
        }
    }

    private static void makeUsersFile(){
        usersFile =new File("Users.txt");
        try {
            if (usersFile.createNewFile()){
                Log.log(Log.INFO,"Users file created");
            }
        } catch (IOException e) {
            Log.log(e);
            e.printStackTrace();
        }
    }
}
