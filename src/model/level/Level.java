package model.level;

import model.animals.AnimalTypes;

import java.util.ArrayList;
import java.util.HashMap;

public class Level {
    // primary model.level properties
    static ArrayList <Level> levelArrayList;
    int initialCoins;
    Task task;
    HashMap <Integer, AnimalTypes> spawn;
    int maxTime;
    int reward;

    public Level(int initialCoins, Task task, HashMap<Integer, AnimalTypes> spawn, int maxTime, int reward) {
        this.initialCoins = initialCoins;
        this.task = task;
        this.spawn = spawn;
        this.maxTime = maxTime;
        this.reward = reward;
    }
}