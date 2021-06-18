package model.level;

import model.animals.AnimalTypes;
import model.products.ProductTypes;

import java.util.ArrayList;
import java.util.HashMap;

public class Level {
    static Level[] levels;
    static int totalLevels;
    // primary Level properties
    int initialCoins;
    HashMap <Integer, AnimalTypes> spawn;
    int maxTime;
    int reward;
    // tasks
    int coinTask;
    HashMap <ProductTypes, Integer> productTasks;
    HashMap <AnimalTypes, Integer> animalTasks;

    public Level(int initialCoins,
                 HashMap<Integer, AnimalTypes> spawn,
                 int maxTime,
                 int reward,
                 int coinTask,
                 HashMap<ProductTypes, Integer> productTasks,
                 HashMap<AnimalTypes, Integer> animalTasks) {
        this.initialCoins = initialCoins;
        this.spawn = spawn;
        this.maxTime = maxTime;
        this.reward = reward;
        this.coinTask = coinTask;
        this.productTasks = productTasks;
        this.animalTasks = animalTasks;
    }

    public static Level[] getLevels() {
        return levels;
    }

    public static void setLevels(Level[] levels) {
        Level.levels = levels;
    }

    public static int getTotalLevels() {
        return totalLevels;
    }

    public static void setTotalLevels(int totalLevels) {
        Level.totalLevels = totalLevels;
    }

    public int getInitialCoins() {
        return initialCoins;
    }

    public HashMap<Integer, AnimalTypes> getSpawn() {
        return spawn;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public int getReward() {
        return reward;
    }

    public int getCoinTask() {
        return coinTask;
    }

    public HashMap<ProductTypes, Integer> getProductTasks() {
        return productTasks;
    }

    public HashMap<AnimalTypes, Integer> getAnimalTasks() {
        return animalTasks;
    }
}