package controller;

import log.Log;
import model.animals.AnimalTypes;
import model.animals.domesticated.AbstractDomesticatedAnimal;
import model.animals.domesticated.Chicken;
import model.animals.pet.AbstractPetAnimal;
import model.animals.wild.AbstractWildAnimal;
import model.level.Level;
import model.products.Product;
import model.products.ProductTypes;

import java.util.ArrayList;
import java.util.HashMap;

public class LevelManager {
    // level manager initialization properties
    Level level; // level that manager is managing
    int levelNo; // level number that manager is managing

    // level properties
    int coins; // coins
    int turn = 0; // number of turns, initial
    int[][] grassArray = new int[6][6]; // grass array
    ArrayList <AbstractDomesticatedAnimal> domesticatedAnimalOnGround;
    ArrayList <AbstractPetAnimal> petAnimalOnGround;
    ArrayList <AbstractWildAnimal> wildAnimalOnGround;
    ArrayList <Product> productOnGround;
    ArrayList <Product> storage;

    // tasks
    int coinTask;
    HashMap <ProductTypes, Integer> productTasks;
    HashMap <AnimalTypes, Integer> animalTasks;

    // task progression
    int coinTaskProgression;
    HashMap <ProductTypes, Integer> productTasksProgression;
    HashMap <AnimalTypes, Integer> animalTasksProgression;

    // progression
    HashMap <Integer, AnimalTypes> spawn;
    int maxTime;
    int reward;

    ArrayList <String> instructionQueue;
    // task booleans
    boolean coinTaskBoolean = false;
    boolean productTasksBoolean = false;
    boolean animalTasksBoolean = false;

    public LevelManager(int levelNo) {
        Log.log(Log.INFO, "LevelManager constructor");
        this.level = Level.getLevels()[levelNo - 1];
        this.levelNo = levelNo;

        coins = level.getInitialCoins();
        turn = 0;

        domesticatedAnimalOnGround = new ArrayList<>();
        petAnimalOnGround = new ArrayList<>();
        wildAnimalOnGround = new ArrayList<>();
        productOnGround = new ArrayList<>();
        storage = new ArrayList<>();

        coinTaskProgression = 0;
        productTasksProgression = new HashMap<>();
        animalTasksProgression = new HashMap<>();


        instructionQueue = new ArrayList<>();
    }

    public boolean addInstruction(String instruction) {
        instructionQueue.add(instruction);
        return true;
    }

    public void dequeueInstruction() {
        Log.log(Log.INFO, "dequeueing instructions");
        for (String instructionString : instructionQueue) {
            Log.log(Log.INFO, "dequeueing " + instructionString);
            if (instructionString.equals("buy chicken")) {
                //TODO
            }
        }
    }

    private void buy(AnimalTypes animalType) {
        if (animalType == AnimalTypes.CHICKEN) {
            if (coins > Chicken.PRICE) {
                domesticatedAnimalOnGround.add(new Chicken());
                coins -= Chicken.PRICE;
                if (level.getAnimalTasks().containsKey(AnimalTypes.CHICKEN)) {
                    //TODO
                }
            }
        }
    }

    public ArrayList<String> getInstructionQueue() {
        return instructionQueue;
    }

    public boolean inquiry() {
        System.out.println("Xx inquiry xX");
        return true;
    }

    public boolean isFinished() {
        return false;
    }
}
