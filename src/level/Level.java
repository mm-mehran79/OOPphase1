package level;

import model.animals.AnimalTypes;

import java.util.HashMap;

public class Level {
    // primary level properties
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