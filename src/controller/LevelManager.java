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
    static final int TRUCK_MAX = 15; // truck max storage
    static final int WELl_MAX = 5; // well max

    // level properties
    boolean isComplete = false; // is the game complete
    int coins; // coins
    int turn = 0; // number of turns, initial
    int[][] grassArray; // grass array
    ArrayList <AbstractDomesticatedAnimal> domesticatedAnimalOnGround;
    ArrayList <AbstractPetAnimal> petAnimalOnGround;
    ArrayList <AbstractWildAnimal> wildAnimalOnGround;
    ArrayList <Product> productOnGround;
    ArrayList <Product> storage;
    int well;

    // turn variant
    ArrayList <Product> truck;

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
        isComplete = false;
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
            else if (instructionString.startsWith("work")) {
                work();
            }
            else if (instructionString.startsWith("cage")) {
                String[] strings = instructionString.split("\\s");
                int x = Integer.parseInt(strings[1]);
                int y = Integer.parseInt(strings[2]);
                cage(x, y);
            }
            else if (instructionString.equals("truck load flour"))
                truckLoad(ProductTypes.FLOUR);
            else if (instructionString.equals("truck load cloth"))
                truckLoad(ProductTypes.CLOTH);
            else if (instructionString.equals("truck load packetmilk"))
                truckLoad(ProductTypes.PACKETMILK);
            else if (instructionString.equals("truck load bread"))
                truckLoad(ProductTypes.BREAD);
            else if (instructionString.equals("truck load shirt"))
                truckLoad(ProductTypes.SHIRT);
            else if (instructionString.equals("truck load icecream"))
                truckLoad(ProductTypes.ICECREAM);
            else if (instructionString.equals("truck load milk"))
                truckLoad(ProductTypes.MILK);
            else if (instructionString.equals("truck load feather"))
                truckLoad(ProductTypes.FEATHER);
            else if (instructionString.equals("truck load egg"))
                truckLoad(ProductTypes.EGG);
            else if (instructionString.equals("truck load lion"))
                truckLoad(ProductTypes.LION);
            else if (instructionString.equals("truck load bear"))
                truckLoad(ProductTypes.BEAR);
            else if (instructionString.equals("truck load tiger"))
                truckLoad(ProductTypes.TIGER);
            else if (instructionString.equals("truck unload flour"))
                truckUnload(ProductTypes.FLOUR);
            else if (instructionString.equals("truck unload cloth"))
                truckUnload(ProductTypes.CLOTH);
            else if (instructionString.equals("truck unload packetmilk"))
                truckUnload(ProductTypes.PACKETMILK);
            else if (instructionString.equals("truck unload bread"))
                truckUnload(ProductTypes.BREAD);
            else if (instructionString.equals("truck unload shirt"))
                truckUnload(ProductTypes.SHIRT);
            else if (instructionString.equals("truck unload icecream"))
                truckUnload(ProductTypes.ICECREAM);
            else if (instructionString.equals("truck unload milk"))
                truckUnload(ProductTypes.MILK);
            else if (instructionString.equals("truck unload feather"))
                truckUnload(ProductTypes.FEATHER);
            else if (instructionString.equals("truck unload egg"))
                truckUnload(ProductTypes.EGG);
            else if (instructionString.equals("truck unload lion"))
                truckUnload(ProductTypes.LION);
            else if (instructionString.equals("truck unload bear"))
                truckUnload(ProductTypes.BEAR);
            else if (instructionString.equals("truck unload tiger"))
                truckUnload(ProductTypes.TIGER);
            else if (instructionString.equals("truck go"))
                truckGo();




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
                    Log.log(Log.ERROR, "pickup no storage" + x + " " + y + ":" + product.getProductType());
                    System.err.println("pickup no storage" + x + " " + y + ":" + product.getProductType());
                }
            }
        }
        if (!emptyPickup) {
            Log.log(Log.ERROR, "pickup no product" + x + " " + y);
            System.err.println("pickup no product" + x + " " + y);
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
            Log.log(Log.INFO, "plant " + x + " " + y);
            System.out.println("plant " + x + " " + y);
            well--;
        }
        else {
            Log.log(Log.ERROR, "plant no water" + x + " " + y);
            System.err.println("plant no water" + x + " " + y);
        }
    }

    private void work() {

    }

    private void cage(int x, int y) {
        boolean cageThrown = false;
        for (AbstractWildAnimal abstractWildAnimal : wildAnimalOnGround) {
            if (abstractWildAnimal.getX() == x && abstractWildAnimal.getY() == y) {
                cageThrown = true;
                abstractWildAnimal.cage();
                Log.log(Log.INFO, "cage @" + x + " " + y + ":" + abstractWildAnimal.getType());
                System.out.println("cage @" + x + " " + y + ":" + abstractWildAnimal.getType());
            }
        }
        if (!cageThrown) {
            Log.log(Log.ERROR, " no cage @" + x + " " + y);
            System.err.println("no cage @" + x + " " + y);
        }
    }

    private void truckLoad(ProductTypes productType) {
        boolean found = false;
        Product productLoad = null;
        for (Product product : storage) {
            if (product.getProductType() == productType && !found) {
                found = true;
                productLoad = product;
            }
        }
        if (productLoad == null) {
            Log.log(Log.ERROR, "truckload no product " + productType);
            System.err.println("truckload no product " + productType);
        }
        else if (productLoad.getStorage() + getTruckStorage() <= TRUCK_MAX) {
            Log.log(Log.INFO, "truckload product " + productType);
            System.out.println("truckload product " + productType);
            truck.add(productLoad);
            storage.remove(productLoad);
        }
        else {
            Log.log(Log.ERROR, "truckload no space " + productType);
            System.err.println("truckload no space " + productType);
        }
    }

    private int getTruckStorage() {
        int truckInt = 0;
        for (Product product : truck) {
            truckInt += product.getStorage();
        }
        Log.log(Log.INFO, "Truck storage = " + truckInt);
        return truckInt;
    }

    private void truckUnload(ProductTypes productType) {
        boolean found = false;
        Product productUnload = null;
        for (Product product : truck) {
            if (product.getProductType() == productType && !found) {
                found = true;
                productUnload = product;
            }
        }
        if (productUnload == null) {
            Log.log(Log.ERROR, "truckUnload no product " + productType);
            System.err.println("truckUnload no product " + productType);
        }
        else if (productUnload.getStorage() + getStorage() <= STORAGE_MAX) {
            Log.log(Log.INFO, "truckUnload product " + productType);
            System.out.println("truckUnload product " + productType);
            storage.add(productUnload);
            truck.remove(productUnload);
        }
        else {
            Log.log(Log.ERROR, "truckUnload no space in storage " + productType);
            System.err.println("truckUnload no space in storage " + productType);
        }
    }

    private void truckGo() {

    }

    public void turnN(int n) {
        inquiry();
    }

    private void turn() {

    }

    public ArrayList<String> getInstructionQueue() {
        return instructionQueue;
    }

    public void inquiry() {
        System.out.println("Xx inquiry xX");
        Log.log(Log.INFO, "Xx   inquiry   xX");

        System.out.println("INQUIRY: number of turn in game = " + turn);
        //Log.log(Log.INFO, "INQUIRY: number of turn in game = " + turn);

        System.out.println("INQUIRY: number of coins = " + coins);
        //Log.log(Log.INFO, "INQUIRY: number of coins = " + coins);

        System.out.println("INQUIRY: grass = ");
        //Log.log(Log.INFO, "INQUIRY: grass");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(grassArray[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println("INQUIRY: domesticated = ");
        //Log.log(Log.INFO, "INQUIRY: domesticated");
        for (AbstractDomesticatedAnimal domesticatedAnimal : domesticatedAnimalOnGround) {
            System.out.println(domesticatedAnimal.getType() + " " +
                    domesticatedAnimal.getHealth() + "% [" +
                    domesticatedAnimal.getX() + " " +
                    domesticatedAnimal.getY() + "]" );
        }

        System.out.println("INQUIRY: wild = ");
        for (AbstractWildAnimal wildAnimal : wildAnimalOnGround) {
            System.out.println(wildAnimal.getType() + " " +
                    wildAnimal.getCagesThrown() + "CT [" +
                    wildAnimal.getX() + " " +
                    wildAnimal.getY() + "]" );
        }

        System.out.println("INQUIRY: pet = ");
        for (AbstractPetAnimal petAnimal : petAnimalOnGround) {
            System.out.println(petAnimal.getType() + " [" +
                    petAnimal.getX() + " " +
                    petAnimal.getY() + "]" );
        }

        System.out.println("INQUIRY: productsOnGround = ");
        for (Product product : productOnGround) {
            System.out.println(product.getProductType() + " " +
                    product.getProductTurns() + "NT [" +
                    product.getX() + " " +
                    product.getY() + "]" );
        }

        System.out.println("INQUIRY: productsOnGround = ");
        for (Product product : productOnGround) {
            System.out.println(product.getProductType() + " " +
                    product.getProductTurns() + "NT [" +
                    product.getX() + " " +
                    product.getY() + "]" );
        }


    }

    public boolean isFinished() {
        return isComplete;
    }
}
