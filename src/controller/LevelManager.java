package controller;

import log.Log;
import model.animals.AnimalTypes;
import model.animals.domesticated.AbstractDomesticatedAnimal;
import model.animals.domesticated.Buffalo;
import model.animals.domesticated.Chicken;
import model.animals.domesticated.Turkey;
import model.animals.pet.AbstractPetAnimal;
import model.animals.pet.Cat;
import model.animals.pet.Dog;
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
    static final int STORAGE_MAX = 30; // max storage

    // level properties
    int coins; // coins
    int turn = 0; // number of turns, initial
    int[][] grassArray; // grass array
    ArrayList <AbstractDomesticatedAnimal> domesticatedAnimalOnGround;
    ArrayList <AbstractPetAnimal> petAnimalOnGround;
    ArrayList <AbstractWildAnimal> wildAnimalOnGround;
    ArrayList <Product> productOnGround;
    ArrayList <Product> storage;

    // tasks
    int coinTask;
    int eggProductTask;
    int featherProductTask;
    int milkProductTask;
    int flourProductTask;
    int clothProductTask;
    int packetmilkProductTask;
    int breadProductTask;
    int shirtProductTask;
    int icecreamProductTask;
    int chickenTasks;
    int turkeyTasks;
    int buffaloTasks;

    // task progression
    int coinTaskProgression;
    int eggProductTaskProgression = 0;
    int featherProductTaskProgression = 0;
    int milkProductTaskProgression = 0;
    int flourProductTaskProgression = 0;
    int clothProductTaskProgression = 0;
    int packetmilkProductTaskProgression = 0;
    int breadProductTaskProgression = 0;
    int shirtProductTaskProgression = 0;
    int icecreamProductTaskProgression = 0;
    int chickenTaskProgression = 0;
    int turkeyTaskProgression = 0;
    int buffaloTaskProgression = 0;

    // task booleans
    boolean coinTaskBoolean = false;
    boolean productTasksBoolean = false;
    boolean animalTasksBoolean = false;


    // time and reward
    HashMap <Integer, AnimalTypes> spawn;
    int maxTime;
    int reward;

    // instruction queue
    ArrayList <String> instructionQueue;

    public LevelManager(int levelNo) {
        // level manager initialization properties
        Log.log(Log.INFO, "LevelManager constructor");
        this.level = Level.getLevels()[levelNo - 1];
        this.levelNo = levelNo;

        // level properties
        coins = level.getInitialCoins();
        turn = 0;
        grassArray = new int[6][6];
        domesticatedAnimalOnGround = new ArrayList<>();
        petAnimalOnGround = new ArrayList<>();
        wildAnimalOnGround = new ArrayList<>();
        productOnGround = new ArrayList<>();
        storage = new ArrayList<>();

        // tasks
        coinTask = level.getCoinTask();
        HashMap <ProductTypes, Integer> productTasks = level.getProductTasks();
        eggProductTask = productTasks.getOrDefault(ProductTypes.EGG, 0);
        featherProductTask = productTasks.getOrDefault(ProductTypes.FEATHER, 0);
        milkProductTask = productTasks.getOrDefault(ProductTypes.MILK, 0);
        flourProductTask = productTasks.getOrDefault(ProductTypes.FLOUR, 0);
        clothProductTask = productTasks.getOrDefault(ProductTypes.CLOTH, 0);
        packetmilkProductTask = productTasks.getOrDefault(ProductTypes.PACKETMILK, 0);
        breadProductTask = productTasks.getOrDefault(ProductTypes.BREAD, 0);
        shirtProductTask = productTasks.getOrDefault(ProductTypes.SHIRT, 0);
        icecreamProductTask = productTasks.getOrDefault(ProductTypes.ICECREAM, 0);
        HashMap <AnimalTypes, Integer> animalTasks = level.getAnimalTasks();
        chickenTasks = animalTasks.getOrDefault(AnimalTypes.CHICKEN, 0);
        turkeyTasks = animalTasks.getOrDefault(AnimalTypes.TURKEY, 0);
        buffaloTasks = animalTasks.getOrDefault(AnimalTypes.BUFFALO, 0);

        // task progression
        coinTaskProgression = level.getInitialCoins();
        eggProductTaskProgression = 0;
        featherProductTaskProgression = 0;
        milkProductTaskProgression = 0;
        flourProductTaskProgression = 0;
        clothProductTaskProgression = 0;
        packetmilkProductTaskProgression = 0;
        breadProductTaskProgression = 0;
        shirtProductTaskProgression = 0;
        icecreamProductTaskProgression = 0;
        chickenTaskProgression = 0;
        turkeyTaskProgression = 0;
        buffaloTaskProgression = 0;

        // task booleans
        coinTaskBoolean = false;
        productTasksBoolean = false;
        animalTasksBoolean = false;

        // time and reward
        spawn = level.getSpawn();
        maxTime = level.getMaxTime();
        reward = level.getReward();

        // instruction queue
        instructionQueue = new ArrayList<>();
    }

    public boolean addInstruction(String instruction) {
        instructionQueue.add(instruction);
        return true;
    }

    public void dequeueInstruction() {
        Log.log(Log.INFO, "dequeuing instructions");
        for (String instructionString : instructionQueue) {
            Log.log(Log.INFO, "dequeuing " + instructionString);

            if (instructionString.equals("buy chicken"))
                buy(AnimalTypes.CHICKEN);
            else if (instructionString.equals("buy turkey"))
                buy(AnimalTypes.TURKEY);
            else if (instructionString.equals("buy buffalo"))
                buy(AnimalTypes.BUFFALO);
            else if (instructionString.equals("buy dog"))
                buy(AnimalTypes.DOG);
            else if (instructionString.equals("buy cat"))
                buy(AnimalTypes.CAT);
            else if (instructionString.startsWith("build")) {

            }
            else if (instructionString.startsWith("pickup")) {

            }
        }
    }

    private void buy(AnimalTypes animalType) {
        if (animalType == AnimalTypes.CHICKEN) {
            if (coins > Chicken.PRICE) {
                domesticatedAnimalOnGround.add(new Chicken());
                coins -= Chicken.PRICE;
                chickenTaskProgression++;
                Log.log(Log.INFO, "bought chicken");
                System.out.println("bought chicken");
            }
            else {
                System.out.println("not enough money for chicken");
                Log.log(Log.INFO,"not enough money for chicken");
            }
        }
        else if (animalType == AnimalTypes.TURKEY) {
            if (coins > Turkey.PRICE) {
                domesticatedAnimalOnGround.add(new Turkey());
                coins -= Turkey.PRICE;
                turkeyTaskProgression++;
                Log.log(Log.INFO, "bought turkey");
                System.out.println( "bought turkey");
            }
            else {
                System.out.println("not enough money for turkey");
                Log.log(Log.INFO,"not enough money for turkey");
            }
        }
        else if (animalType == AnimalTypes.BUFFALO) {
            if (coins > Buffalo.PRICE) {
                domesticatedAnimalOnGround.add(new Buffalo());
                coins -= Buffalo.PRICE;
                buffaloTaskProgression++;
                Log.log(Log.INFO, "bought buffalo");
                System.out.println("bought buffalo");
            }
            else {
                System.out.println("not enough money for buffalo");
                Log.log(Log.INFO,"not enough money for buffalo");
            }
        }
        else if (animalType == AnimalTypes.DOG) {
            if (coins > Dog.PRICE) {
                petAnimalOnGround.add(new Dog());
                coins -= Dog.PRICE;
                Log.log(Log.INFO, "bought dog");
                System.out.println("bought dog");
            }
            else {
                System.out.println("not enough money for dog");
                Log.log(Log.INFO,"not enough money for dog");
            }
        }
        else if (animalType == AnimalTypes.CAT) {
            if (coins > Cat.PRICE) {
                petAnimalOnGround.add(new Cat());
                coins -= Cat.PRICE;
                Log.log(Log.INFO, "bought cat");
                System.out.println("bought cat");
            }
            else {
                System.out.println("not enough money for cat");
                Log.log(Log.INFO,"not enough money for cat");
            }
        }
    }

    public ArrayList<String> getInstructionQueue() {
        return instructionQueue;
    }

    public void inquiry() {
        System.out.println("Xx inquiry xX");
    }

    public boolean isFinished() {
        return false;
    }
}
