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
    static final int WELl_MAX = 5; // max storage

    // level properties
    int coins; // coins
    int turn = 0; // number of turns, initial
    int[][] grassArray; // grass array
    ArrayList <AbstractDomesticatedAnimal> domesticatedAnimalOnGround;
    ArrayList <AbstractPetAnimal> petAnimalOnGround;
    ArrayList <AbstractWildAnimal> wildAnimalOnGround;
    ArrayList <Product> productOnGround;
    ArrayList <Product> storage;
    int well;

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
        well = WELl_MAX;

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

    public void addInstruction(String instruction) {
        instructionQueue.add(instruction);
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
                build();
            }
            else if (instructionString.startsWith("pickup")) {
                String[] strings = instructionString.split("\\s");
                int x = Integer.parseInt(strings[1]);
                int y = Integer.parseInt(strings[2]);
                pickup(x, y);
            }
            else if (instructionString.equals("well")) {
                well();
            }
            else if (instructionString.startsWith("plant")) {
                String[] strings = instructionString.split("\\s");
                int x = Integer.parseInt(strings[1]);
                int y = Integer.parseInt(strings[2]);
                plant(x, y);
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
                System.err.println("not enough money for chicken");
                Log.log(Log.ERROR,"not enough money for chicken");
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
                System.err.println("not enough money for turkey");
                Log.log(Log.ERROR,"not enough money for turkey");
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
                System.err.println("not enough money for buffalo");
                Log.log(Log.ERROR,"not enough money for buffalo");
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
                System.err.println("not enough money for dog");
                Log.log(Log.ERROR,"not enough money for dog");
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
                System.err.println("not enough money for cat");
                Log.log(Log.ERROR,"not enough money for cat");
            }
        }
    }

    private void build() {

    }

    private void pickup(int x, int y) {
        boolean emptyPickup = false;
        ArrayList <Product> offGround = new ArrayList<>();
        for (Product product : productOnGround) {
            if (product.getX() == x && product.getY() == y) {
                emptyPickup = true;
                if (getStorage() + product.getStorage() <= STORAGE_MAX) {
                    storage.add(product);
                    offGround.add(product);
                    Log.log(Log.INFO, "pickup " + x + " " + y + ":" + product.getProductType());
                    System.out.println("pickup " + x + " " + y + ":" + product.getProductType());
                }
                else {
                    Log.log(Log.ERROR, "x pickup s" + x + " " + y + ":" + product.getProductType());
                    System.err.println("x pickup s" + x + " " + y + ":" + product.getProductType());
                }
            }
        }
        if (emptyPickup) {
            Log.log(Log.ERROR, "x pickup x" + x + " " + y);
            System.err.println("x pickup x" + x + " " + y);
        }
        productOnGround.remove(offGround);
    }

    private int getStorage() {
        int storageInt = 0;
        for (Product product : storage) {
            storageInt += product.getStorage();
        }
        Log.log(Log.INFO, "Storage = " + storageInt);
        return storageInt;
    }

    private void well() {

    }

    private void plant(int x, int y) {
        if (well != 0) {
            grassArray[x][y]++;

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
