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
import model.animals.wild.Bear;
import model.animals.wild.Lion;
import model.animals.wild.Tiger;
import model.level.Level;
import model.products.Product;
import model.products.ProductTypes;
import model.workshop.Workshop;
import org.omg.PortableInterceptor.INACTIVE;
import java.util.Collections;

import java.util.ArrayList;
import java.util.HashMap;

public class LevelManager {
    // level manager initialization properties
    Level level; // level that manager is managing
    int levelNo; // level number that manager is managing
    static final int STORAGE_MAX = 30; // max storage
    static final int TRUCK_MAX = 15; // truck max storage
    static final int WELl_MAX = 5; // well max
    static final int TRUCK_MAX_TURNS = 10; // go and back truck turns
    static final int WELL_MAX_TURNS = 3; // go and back well turns

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

    // workshop things
    boolean workshopFlour = false;
    boolean workshopCloth = false;
    boolean workshopPacketmilk = false;
    boolean workshopBread = false;
    boolean workshopShirt = false;
    boolean workshopIcecream = false;

    Workshop flour;
    Workshop cloth;
    Workshop packetmilk;
    Workshop bread;
    Workshop shirt;
    Workshop icecream;

    // truck properties
    int truckTurns;
    int truckTempMoney;
    boolean truckIsAvailable;

    // well properties
    int wellTurns;
    boolean wellIsAvailable;

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

    public LevelManager(int levelNo, int cumulativeReward) {
        // level manager initialization properties
        Log.log(Log.INFO, "LevelManager constructor");
        this.level = Level.getLevels()[levelNo - 1];
        this.levelNo = levelNo;

        // level properties
        isComplete = false;
        coins = level.getInitialCoins() + cumulativeReward;
        turn = 0;
        grassArray = new int[6][6];
        domesticatedAnimalOnGround = new ArrayList<>();
        petAnimalOnGround = new ArrayList<>();
        wildAnimalOnGround = new ArrayList<>();
        productOnGround = new ArrayList<>();
        storage = new ArrayList<>();
        well = WELl_MAX;

        // turn variant
        truck = new ArrayList<>();

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
        coinTaskProgression = level.getInitialCoins() + cumulativeReward;
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

        // workshop things
        workshopFlour = false;
        workshopCloth = false;
        workshopPacketmilk = false;
        workshopBread = false;
        workshopShirt = false;
        workshopIcecream = false;

        flour = new Workshop(ProductTypes.FLOUR);
        cloth = new Workshop(ProductTypes.CLOTH);
        packetmilk = new Workshop(ProductTypes.PACKETMILK);
        bread = new Workshop(ProductTypes.BREAD);
        shirt = new Workshop(ProductTypes.SHIRT);
        icecream = new Workshop(ProductTypes.ICECREAM);

        // truck properties
        truckTurns = 0;
        truckTempMoney = 0;
        truckIsAvailable = true;

        // well properties
        wellTurns = 0;
        wellIsAvailable = true;

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
            else if (instructionString.equals("build flour"))
                build(ProductTypes.FLOUR);
            else if (instructionString.equals("build cloth"))
                build(ProductTypes.CLOTH);
            else if (instructionString.equals("build packetmilk"))
                build(ProductTypes.PACKETMILK);
            else if (instructionString.equals("build bread"))
                build(ProductTypes.BREAD);
            else if (instructionString.equals("build shirt"))
                build(ProductTypes.SHIRT);
            else if (instructionString.equals("build icecream"))
                build(ProductTypes.ICECREAM);
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
                work(instructionString);
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
        Log.log(Log.INFO, "dequeuing instructions completed");
        Log.log(Log.INFO, "dequeuing instructions completed, CLEARING instructionQueue LIST");
        instructionQueue.clear();
    }

    private void buy(AnimalTypes animalType) {
        if (animalType == AnimalTypes.CHICKEN) {
            if (coins >= Chicken.PRICE) {
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
            if (coins >= Turkey.PRICE) {
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
            if (coins >= Buffalo.PRICE) {
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
            if (coins >= Dog.PRICE) {
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
            if (coins >= Cat.PRICE) {
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

    private void build(ProductTypes productType) {
        if (productType == ProductTypes.FLOUR) {
            if (workshopFlour) {
                Log.log(Log.ERROR, "build flour: you own workshopFlour");
                System.err.println("build flour: you own workshopFlour");
            }
            else {
                if (150 <= coins) {
                    coins -= 150;
                    workshopFlour = true;
                    Log.log(Log.INFO, "build flour: built workshopFlour");
                    System.out.println("build flour: built workshopFlour");
                }
                else {
                    Log.log(Log.ERROR, "build flour: not enough money for workshopFlour");
                    System.err.println("build flour: not enough money for workshopFlour");
                }
            }
        }
        else if (productType == ProductTypes.CLOTH) {
            if (workshopCloth) {
                Log.log(Log.ERROR, "build cloth: you own workshopCloth");
                System.err.println("build cloth: you own workshopCloth");
            }
            else {
                if (250 <= coins) {
                    coins -= 250;
                    workshopCloth = true;
                    Log.log(Log.INFO, "build cloth: built workshopCloth");
                    System.out.println("build cloth: built workshopCloth");
                }
                else {
                    Log.log(Log.ERROR, "build cloth: not enough money for workshopCloth");
                    System.err.println("build cloth: not enough money for workshopCloth");
                }
            }
        }
        else if (productType == ProductTypes.PACKETMILK) {
            if (workshopPacketmilk) {
                Log.log(Log.ERROR, "build packetmilk: you own workshopPacketmilk");
                System.err.println("build packetmilk: you own workshopPacketmilk");
            }
            else {
                if (400 <= coins) {
                    coins -= 400;
                    workshopPacketmilk = true;
                    Log.log(Log.INFO, "build packetmilk: built workshopPacketmilk");
                    System.out.println("build packetmilk: built workshopPacketmilk");
                }
                else {
                    Log.log(Log.ERROR, "build packetmilk: not enough money for workshopPacketmilk");
                    System.err.println("build packetmilk: not enough money for workshopPacketmilk");
                }
            }
        }
        else if (productType == ProductTypes.BREAD) {
            if (workshopBread) {
                Log.log(Log.ERROR, "build bread: you own workshopBread");
                System.err.println("build bread: you own workshopBread");
            }
            else {
                if (250 <= coins) {
                    coins -= 250;
                    workshopBread = true;
                    Log.log(Log.INFO, "build bread: built workshopBread");
                    System.out.println("build bread: built workshopBread");
                }
                else {
                    Log.log(Log.ERROR, "build bread: not enough money for workshopBread");
                    System.err.println("build bread: not enough money for workshopBread");
                }
            }
        }
        else if (productType == ProductTypes.SHIRT) {
            if (workshopShirt) {
                Log.log(Log.ERROR, "build shirt: you own workshopShirt");
                System.err.println("build shirt: you own workshopShirt");
            }
            else {
                if (400 <= coins) {
                    coins -= 400;
                    workshopShirt = true;
                    Log.log(Log.INFO, "build shirt: built workshopShirt");
                    System.out.println("build shirt: built workshopShirt");
                }
                else {
                    Log.log(Log.ERROR, "build shirt: not enough money for workshopShirt");
                    System.err.println("build shirt: not enough money for workshopShirt");
                }
            }
        }
        else if (productType == ProductTypes.ICECREAM) {
            if (workshopIcecream) {
                Log.log(Log.ERROR, "build icecream: you own workshopIcecream");
                System.err.println("build icecream: you own workshopIcecream");
            }
            else {
                if (550 <= coins) {
                    coins -= 550;
                    workshopIcecream = true;
                    Log.log(Log.INFO, "build icecream: built workshopIcecream");
                    System.out.println("build icecream: built workshopIcecream");
                }
                else {
                    Log.log(Log.ERROR, "build icecream: not enough money for workshopIcecream");
                    System.err.println("build icecream: not enough money for workshopIcecream");
                }
            }
        }
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
                    if (product.getProductType() == ProductTypes.EGG)
                        eggProductTaskProgression++;
                    else if (product.getProductType() == ProductTypes.FEATHER)
                        featherProductTaskProgression++;
                    else if (product.getProductType() == ProductTypes.MILK)
                        milkProductTaskProgression++;
                    else if (product.getProductType() == ProductTypes.FLOUR)
                        flourProductTaskProgression++;
                    else if (product.getProductType() == ProductTypes.CLOTH)
                        clothProductTaskProgression++;
                    else if (product.getProductType() == ProductTypes.PACKETMILK)
                        packetmilkProductTaskProgression++;
                    else if (product.getProductType() == ProductTypes.BREAD)
                        breadProductTaskProgression++;
                    else if (product.getProductType() == ProductTypes.SHIRT)
                        shirtProductTaskProgression++;
                    else if (product.getProductType() == ProductTypes.ICECREAM)
                        icecreamProductTaskProgression++;
                }
                else {
                    Log.log(Log.ERROR, "pickup no storage " + x + " " + y + ":" + product.getProductType());
                    System.err.println("pickup no storage " + x + " " + y + ":" + product.getProductType());
                }
            }
        }
        if (!emptyPickup) {
            Log.log(Log.ERROR, "pickup no product " + x + " " + y);
            System.err.println("pickup no product " + x + " " + y);
        }
        productOnGround.removeAll(offGround);
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
        if (!wellIsAvailable) {
            System.err.println("well is unavailable (being used)");
            Log.log(Log.ERROR, "well is unavailable (being used)");
            return;
        }
        else if (well != 0) {
            System.err.println("well isn't empty (should be empty to refill)");
            Log.log(Log.ERROR, "well isn't empty (should be empty to refill)");
            return;
        }
        wellIsAvailable = false;
        wellTurns = 0;
    }

    private void plant(int x, int y) {
        if (well != 0) {
            grassArray[x][y]++;
            Log.log(Log.INFO, "plant " + x + " " + y);
            System.out.println("plant " + x + " " + y);
            well--;
        }
        else {
            Log.log(Log.ERROR, "plant no water " + x + " " + y);
            System.err.println("plant no water " + x + " " + y);
        }
    }

    private void work(String string) {
        if (string.equals("work flour"))
            workshopFlour();
        else if (string.equals("work cloth"))
            workshopCloth();
        else if (string.equals("work packetmilk"))
            workshopPacketmilk();
        else if (string.equals("work bread"))
            workshopBread();
        else if (string.equals("work shirt"))
            workshopShirt();
        else if (string.equals("work icecream"))
            workshopIcecream();
    }

    private void workshopFlour() {
        if (!workshopFlour) {
            if (flour.isAvailable()) {
                if (getProductTypeAndRemove(ProductTypes.EGG)) {
                    flour.make();
                    Log.log(Log.INFO, "egg removed from storage, build flour starting to work");
                    System.out.println("egg removed from storage, build flour starting to work");
                }
                else {
                    Log.log(Log.ERROR, "no egg for build flour to starting working");
                    System.err.println("no egg for build flour to starting working");
                }
            }
            else {
                Log.log(Log.ERROR, "build flour is working");
                System.err.println("build flour is working");
            }
        }
        else {
            Log.log(Log.ERROR, "you don't have build flour");
            System.err.println("you don't have build flour");
        }
    }

    private void workshopCloth() {
        if (!workshopCloth) {
            if (cloth.isAvailable()) {
                if (getProductTypeAndRemove(ProductTypes.FEATHER)) {
                    cloth.make();
                    Log.log(Log.INFO, "feather removed from storage, build cloth starting to work");
                    System.out.println("feather removed from storage, build cloth starting to work");
                }
                else {
                    Log.log(Log.ERROR, "no feather for build cloth to starting working");
                    System.err.println("no feather for build cloth to starting working");
                }
            }
            else {
                Log.log(Log.ERROR, "build cloth is working");
                System.err.println("build cloth is working");
            }
        }
        else {
            Log.log(Log.ERROR, "you don't have build cloth");
            System.err.println("you don't have build cloth");
        }
    }

    private void workshopPacketmilk() {
        if (!workshopPacketmilk) {
            if (packetmilk.isAvailable()) {
                if (getProductTypeAndRemove(ProductTypes.MILK)) {
                    packetmilk.make();
                    Log.log(Log.INFO, "milk removed from storage, build packetmilk starting to work");
                    System.out.println("milk removed from storage, build packetmilk starting to work");
                }
                else {
                    Log.log(Log.ERROR, "no milk for build packetmilk to starting working");
                    System.err.println("no milk for build packetmilk to starting working");
                }
            }
            else {
                Log.log(Log.ERROR, "build packetmilk is working");
                System.err.println("build packetmilk is working");
            }
        }
        else {
            Log.log(Log.ERROR, "you don't have build packetmilk");
            System.err.println("you don't have build packetmilk");
        }
    }

    private void workshopBread() {
        if (!workshopBread) {
            if (bread.isAvailable()) {
                if (getProductTypeAndRemove(ProductTypes.FLOUR)) {
                    bread.make();
                    Log.log(Log.INFO, "flour removed from storage, build bread starting to work");
                    System.out.println("flour removed from storage, build bread starting to work");
                }
                else {
                    Log.log(Log.ERROR, "no flour for build bread to starting working");
                    System.err.println("no flour for build bread to starting working");
                }
            }
            else {
                Log.log(Log.ERROR, "build bread is working");
                System.err.println("build bread is working");
            }
        }
        else {
            Log.log(Log.ERROR, "you don't have build bread");
            System.err.println("you don't have build bread");
        }
    }

    private void workshopShirt() {
        if (!workshopShirt) {
            if (shirt.isAvailable()) {
                if (getProductTypeAndRemove(ProductTypes.CLOTH)) {
                    shirt.make();
                    Log.log(Log.INFO, "cloth removed from storage, build shirt starting to work");
                    System.out.println("cloth removed from storage, build shirt starting to work");
                }
                else {
                    Log.log(Log.ERROR, "no cloth for build shirt to starting working");
                    System.err.println("no cloth for build shirt to starting working");
                }
            }
            else {
                Log.log(Log.ERROR, "build shirt is working");
                System.err.println("build shirt is working");
            }
        }
        else {
            Log.log(Log.ERROR, "you don't have build shirt");
            System.err.println("you don't have build shirt");
        }
    }

    private void workshopIcecream() {
        if (!workshopIcecream) {
            if (icecream.isAvailable()) {
                if (getProductTypeAndRemove(ProductTypes.PACKETMILK)) {
                    icecream.make();
                    Log.log(Log.INFO, "packetmilk removed from storage, build icecream starting to work");
                    System.out.println("packetmilk removed from storage, build icecream starting to work");
                }
                else {
                    Log.log(Log.ERROR, "no packetmilk for build icecream to starting working");
                    System.err.println("no packetmilk for build icecream to starting working");
                }
            }
            else {
                Log.log(Log.ERROR, "build icecream is working");
                System.err.println("build icecream is working");
            }
        }
        else {
            Log.log(Log.ERROR, "you don't have build icecream");
            System.err.println("you don't have build icecream");
        }
    }

    private boolean getProductTypeAndRemove(ProductTypes type) {
        for (Product product : storage) {
            if(product.getProductType() == type) {
                storage.remove(product);
                return true;
            }
        }
        return false;
    }

    private boolean hasProduct(ProductTypes type) {
        for (Product product : storage) {
            if(product.getProductType() == type)
                return true;
        }
        return false;
    }

    private void cage(int x, int y) {
        boolean cageThrown = false;
        for (AbstractWildAnimal abstractWildAnimal : wildAnimalOnGround) {
            if (abstractWildAnimal.getX() == x && abstractWildAnimal.getY() == y) {
                cageThrown = true;
                abstractWildAnimal.cage();
                Log.log(Log.INFO, "cage @ " + x + " " + y + ":" + abstractWildAnimal.getType());
                System.out.println("cage @ " + x + " " + y + ":" + abstractWildAnimal.getType());
            }
        }
        if (!cageThrown) {
            Log.log(Log.ERROR, " no cage @ " + x + " " + y);
            System.err.println("no cage @ " + x + " " + y);
        }
    }

    private void truckLoad(ProductTypes productType) {
        if (!truckIsAvailable) {
            System.err.println("truck is unavailable (being used)");
            Log.log(Log.ERROR, "truck is unavailable (being used)");
            return;
        }
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
            truck.add(productLoad);
            storage.remove(productLoad);
            Log.log(Log.INFO, "truckload product " + productType + "coins on truck = " + getTruckStorageMoney());
            System.out.println("truckload product " + productType + "coins on truck = " + getTruckStorageMoney());
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

    private int getTruckStorageMoney() {
        int truckStorageMoney = 0;
        for (Product product : truck) {
            truckStorageMoney += product.getPrice();
        }
        return truckStorageMoney;
    }

    private void truckUnload(ProductTypes productType) {
        if (!truckIsAvailable) {
            System.err.println("truck is unavailable (being used)");
            Log.log(Log.ERROR, "truck is unavailable (being used)");
            return;
        }
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
            storage.add(productUnload);
            truck.remove(productUnload);
            Log.log(Log.INFO, "truckUnload product " + productType + "coins on truck = " + getTruckStorageMoney());
            System.out.println("truckUnload product " + productType + "coins on truck = " + getTruckStorageMoney());
        }
        else {
            Log.log(Log.ERROR, "truckUnload no space in storage " + productType);
            System.err.println("truckUnload no space in storage " + productType);
        }
    }

    private void truckGo() {
        if (!truckIsAvailable) {
            System.err.println("truck is unavailable (being used)");
            Log.log(Log.ERROR, "truck is unavailable (being used)");
            return;
        }
        truckIsAvailable = false;
        truckTempMoney = 0;
        for (Product product : truck) {
            truckTempMoney += product.getPrice();
        }
        truckTurns = 0;
        System.err.println("truck is going now with coins going to += " + truckTempMoney);
        Log.log(Log.ERROR, "truck is going now with coins going to += " + truckTempMoney);
    }

    public void turnN(int n) {
        Log.log(Log.INFO, "it is levelManager's TurnN with N = " + n);
        System.out.println("it is levelManager's TurnN with N = " + n);
        dequeueInstruction();

        for (int i = 1; i <= n; i++) {
            System.out.println("levelManager's Turn, with turn no = " + i);
            Log.log(Log.INFO, "levelManager's Turn, with turn no = " + i);
            turn();
            System.out.println("levelManager's Turn inquiry, with turn no = " + i);
            Log.log(Log.INFO, "levelManager's Turn inquiry, with turn no = " + i);
            inquiry();
        }

        Log.log(Log.INFO, "DONE, levelManager's TurnN with N = " + n);
        System.out.println("DONE, it is levelManager's TurnN with N = " + n);
    }

    private void turn() {
        Log.log(Log.INFO, "it is levelManager's turn for each TurnN's N");
        System.out.println("it is levelManager's turn for each TurnN's N");

        Log.log(Log.INFO, "levelManager: addTurn");
        System.out.println("levelManager: addTurn");
        turn++;
        for (AbstractDomesticatedAnimal domesticatedAnimal : domesticatedAnimalOnGround) {
            domesticatedAnimal.addTurn();
        }
        for (AbstractPetAnimal petAnimal : petAnimalOnGround) {
            petAnimal.addTurn();
        }
        for (AbstractWildAnimal wildAnimal : wildAnimalOnGround) {
            wildAnimal.addTurn();
        }
        for (Product product : productOnGround) {
            product.addTurn();
        }
        flour.addTurn();
        cloth.addTurn();
        packetmilk.addTurn();
        bread.addTurn();
        shirt.addTurn();
        icecream.addTurn();
        truckTurns++;
        wellTurns++;

        Log.log(Log.INFO, "levelManager: getting product from WS, well &  animals");
        System.out.println("levelManager: getting product from WS, well &  animals");

        if (flour.setZero()) {
            Product product = new Product(ProductTypes.FLOUR);
            flourProductTaskProgression++;
            productOnGround.add(product);
            Log.log(Log.INFO, "levelManager: flour WS done, flour product added to ground @ [" +
                    product.getX() + " " + product.getY() + "]");
            System.out.println("levelManager: flour WS done, flour product added to ground @ [" +
                    product.getX() + " " + product.getY() + "]");
        }

        if (cloth.setZero()) {
            Product product = new Product(ProductTypes.CLOTH);
            clothProductTaskProgression++;
            productOnGround.add(product);
            Log.log(Log.INFO, "levelManager: cloth WS done, cloth product added to ground @ [" +
                    product.getX() + " " + product.getY() + "]");
            System.out.println("levelManager: cloth WS done, cloth product added to ground @ [" +
                    product.getX() + " " + product.getY() + "]");
        }

        if (packetmilk.setZero()) {
            Product product =new Product(ProductTypes.PACKETMILK);
            packetmilkProductTaskProgression++;
            productOnGround.add(product);
            Log.log(Log.INFO, "levelManager: packetmilk WS done, packetmilk product added to ground @ [" +
                    product.getX() + " " + product.getY() + "]");
            System.out.println("levelManager: packetmilk WS done, packetmilk product added to ground @ [" +
                    product.getX() + " " + product.getY() + "]");
        }

        if (bread.setZero()) {
            Product product = new Product(ProductTypes.BREAD);
            breadProductTaskProgression++;
            productOnGround.add(product);
            Log.log(Log.INFO, "levelManager: bread WS done, bread product added to ground @ [" +
                    product.getX() + " " + product.getY() + "]");
            System.out.println("levelManager: bread WS done, bread product added to ground @ [" +
                    product.getX() + " " + product.getY() + "]");
        }

        if (shirt.setZero()) {
            Product product = new Product(ProductTypes.SHIRT);
            shirtProductTaskProgression++;
            productOnGround.add(product);
            Log.log(Log.INFO, "levelManager: shirt WS done, shirt product added to ground @ [" +
                    product.getX() + " " + product.getY() + "]");
            System.out.println("levelManager: shirt WS done, shirt product added to ground @ [" +
                    product.getX() + " " + product.getY() + "]");
        }

        if (icecream.setZero()) {
            Product product = new Product(ProductTypes.ICECREAM);
            icecreamProductTaskProgression++;
            productOnGround.add(product);
            Log.log(Log.INFO, "levelManager: icecream WS done, icecream product added to ground @ [" +
                    product.getX() + " " + product.getY() + "]");
            System.out.println("levelManager: icecream WS done, icecream product added to ground @ [" +
                    product.getX() + " " + product.getY() + "]");
        }

        if (!wellIsAvailable && (wellTurns >= WELL_MAX_TURNS)) {
            wellIsAvailable = true;
            wellTurns = 0;
            well = WELl_MAX;
            Log.log(Log.INFO, "levelManager: well is full and available");
            System.out.println("levelManager: well is full and available");
        }

        if (!truckIsAvailable && (truckTurns >= TRUCK_MAX_TURNS)) {
            Log.log(Log.INFO, "levelManager: truck came back and is available with coins += " + truckTempMoney);
            System.out.println("levelManager: truck came back and is available with coins += " + truckTempMoney);
            truckIsAvailable = true;
            truckTurns = 0;
            coins += truckTempMoney;
            coinTaskProgression += truckTempMoney;
            truckTempMoney = 0;
            truck.clear();
        }

        for (AbstractDomesticatedAnimal domesticatedAnimal : domesticatedAnimalOnGround) {
            Product product = domesticatedAnimal.giveProduct();
            if (product != null) {
                productOnGround.add(product);
                System.out.println("levelManager: " + domesticatedAnimal.getType().toString() + " got product on ground @ [" +
                        product.getX() + " " + product.getY() + "]");
                Log.log(Log.INFO, "levelManager: " + domesticatedAnimal.getType().toString() + " got product on ground @ [" +
                        product.getX() + " " + product.getY() + "]");
            }
        }

        // now moving

        Log.log(Log.INFO, "levelManager: moving & eating & dying domestic animals");
        System.out.println("levelManager: moving & eating & dying domestic animals");
        for (AbstractDomesticatedAnimal domesticatedAnimal : domesticatedAnimalOnGround) {
            domesticatedAnimal.move();
        }

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                ArrayList<AbstractDomesticatedAnimal> domesticatedAnimalCoordination = new ArrayList<>();
                for (AbstractDomesticatedAnimal domesticatedAnimal : domesticatedAnimalOnGround) {
                    if (domesticatedAnimal.getX() == i && domesticatedAnimal.getY() == j)
                        domesticatedAnimalCoordination.add(domesticatedAnimal);
                }
                Collections.sort(domesticatedAnimalCoordination);
                for (AbstractDomesticatedAnimal domesticatedAnimal : domesticatedAnimalCoordination) {
                    domesticatedAnimal.eat(grassArray);
                }
            }
        }

        ArrayList<AbstractDomesticatedAnimal> deadDomesticatedAnimal = new ArrayList<>();
        for (AbstractDomesticatedAnimal domesticatedAnimal : domesticatedAnimalOnGround) {
            if (!domesticatedAnimal.hunger()) {
                deadDomesticatedAnimal.add(domesticatedAnimal);
                System.err.println(domesticatedAnimal.getType().toString() + " @ [" + domesticatedAnimal.getX() + " " +
                                    domesticatedAnimal.getY() + "] died from hunger");
                Log.log(Log.ERROR, domesticatedAnimal.getType().toString() + " @ [" + domesticatedAnimal.getX() + " " +
                        domesticatedAnimal.getY() + "] died from hunger");
            }
        }
        domesticatedAnimalOnGround.removeAll(deadDomesticatedAnimal);

        Log.log(Log.INFO, "levelManager: moving & doing domestic animals");
        System.out.println("levelManager: moving & doing domestic animals");

        ArrayList<AbstractPetAnimal> deadDogAnimal = new ArrayList<>();
        for (AbstractPetAnimal petAnimal : petAnimalOnGround) {
            petAnimal.move();

            if (petAnimal.getType() == AnimalTypes.CAT) {
                Log.log(Log.INFO, "levelManager: cat picking up:");
                System.out.println("levelManager: cat picking up:");

                pickup(petAnimal.getX(), petAnimal.getY());
            }

            if (petAnimal.getType() == AnimalTypes.DOG) {
                for (AbstractWildAnimal wildAnimal : wildAnimalOnGround) {
                    if (wildAnimal.getX() == petAnimal.getX() && wildAnimal.getX() == petAnimal.getX()) {
                        deadDogAnimal.add(petAnimal);
                        System.out.println("dog and wild animal died @ [" + petAnimal.getX() + " " +
                                            petAnimal.getY() + "]");
                        Log.log(Log.INFO, "dog and wild animal died @ [" + petAnimal.getX() + " " +
                                                    petAnimal.getY() + "]");
                        wildAnimalOnGround.remove(wildAnimal);
                        if (wildAnimal.getType() == AnimalTypes.BEAR)
                            productOnGround.add(new Product(ProductTypes.BEAR));
                        else if (wildAnimal.getType() == AnimalTypes.LION)
                            productOnGround.add(new Product(ProductTypes.LION));
                        else if (wildAnimal.getType() == AnimalTypes.TIGER)
                            productOnGround.add(new Product(ProductTypes.TIGER));

                        break;
                    }
                }
            }
        }
        petAnimalOnGround.removeAll(deadDogAnimal);

        ArrayList <AbstractWildAnimal> wildAnimalCaged = new ArrayList<>();
        for (AbstractWildAnimal wildAnimal : wildAnimalOnGround) {
            if (wildAnimal.isCaged) {
                wildAnimalCaged.add(wildAnimal);
                System.out.println("wild animal @ [" + wildAnimal.getX() + " " + wildAnimal.getY() + "] caged to product");
                Log.log(Log.INFO,
                        "wild animal @ [" + wildAnimal.getX() + " " + wildAnimal.getY() + "] caged to product");
                continue;
            }

            wildAnimal.move();

            AbstractDomesticatedAnimal wildAnimalPreyObject = wildAnimalPrey(wildAnimal.getX(), wildAnimal.getY());
            if (wildAnimalPreyObject != null) {
                domesticatedAnimalOnGround.remove(wildAnimalPreyObject);
                System.out.println("DomesticatedAnimal died @ [" + wildAnimalPreyObject.getX() + " " +
                        wildAnimalPreyObject.getY() + "]");
                Log.log(Log.INFO, "DomesticatedAnimal died @ [" + wildAnimalPreyObject.getX() + " " +
                        wildAnimalPreyObject.getY() + "]");
            }

        }
        wildAnimalOnGround.removeAll(wildAnimalCaged);



        AnimalTypes spawnAnimalTypes = spawn.getOrDefault(turn, null);
        if (spawnAnimalTypes != null) {
            AbstractWildAnimal wildAnimal;
            if (spawnAnimalTypes == AnimalTypes.LION)
                wildAnimal = new Lion(turn);
            else if (spawnAnimalTypes == AnimalTypes.TIGER)
                wildAnimal = new Tiger(turn);
            else
                wildAnimal = new Bear(turn);

            wildAnimalOnGround.add(wildAnimal);
            Log.log(Log.INFO, wildAnimal.getType() + " spawned @ [" + wildAnimal.getX() + " " +
                        wildAnimal.getY() + "]");
            System.out.println(wildAnimal.getType() + " spawned @ [" + wildAnimal.getX() + " " +
                                wildAnimal.getY() + "]");
        }

        Log.log(Log.INFO, "levelManager: removing productOnGround:");
        System.out.println("levelManager: removing productOnGround:");
        ArrayList <Product> removeTimeProductOnGround = new ArrayList<>();
        for (Product product : productOnGround) {
            if (product.getProductTurns() > product.getMaxTurns()) {
                removeTimeProductOnGround.add(product);
                System.err.println(product.getProductType() + " productOnGround removed max time from @ [" +
                                    product.getX() + " " + product.getY() + "]");
                Log.log(Log.ERROR, product.getProductType() + " productOnGround removed max time from @ [" +
                                            product.getX() + " " + product.getY() + "]");
            }
        }
        productOnGround.removeAll(removeTimeProductOnGround);

        Log.log(Log.INFO, "done levelManager's turn for each TurnN's N done");
        System.out.println("done levelManager's turn for each TurnN's N done");
    }

    private AbstractDomesticatedAnimal wildAnimalPrey(int x, int y) {
        AbstractDomesticatedAnimal domesticatedAnimal = null;
        for (AbstractDomesticatedAnimal animal : domesticatedAnimalOnGround) {
            if (animal.getX() == x && animal.getY() == y)
                return animal;
        }
        return null;
    }

    public ArrayList<String> getInstructionQueue() { return instructionQueue; }

    public void inquiry() {
        System.out.println("Xx\tinquiry\txX");
        Log.log(Log.INFO, "Xx\tinquiry\txX");

        System.out.println("INQUIRY: number of turn in game = " + turn);
        Log.log(Log.INFO, "INQUIRY: number of turn in game = " + turn);
        System.out.println("INQUIRY: maxTime for reward = " + maxTime);
        Log.log(Log.INFO, "INQUIRY: maxTime for reward = " + maxTime);
        System.out.println("INQUIRY: Reward = " + reward);
        Log.log(Log.INFO, "INQUIRY: Reward = " + reward);

        System.out.println("INQUIRY: number of coins = " + coins);
        Log.log(Log.INFO, "INQUIRY: number of coins = " + coins);

        System.out.println("INQUIRY: grass = ");
        Log.log(Log.INFO, "INQUIRY: grass");
        String grassStringLog;
        for (int i = 0; i < 6; i++) {
            System.out.print(i + "r : ");
            for (int j = 0; j < 6; j++) {
                System.out.print(grassArray[i][j] + " _ ");
            }
            grassStringLog = i + "r : " + grassArray[i][0] + " _ "
                                        + grassArray[i][1] + " _ "
                                        + grassArray[i][2] + " _ "
                                        + grassArray[i][3] + " _ "
                                        + grassArray[i][4] + " _ "
                                        + grassArray[i][5] + " _ ";
            Log.log(Log.INFO, grassStringLog);
            System.out.println();
        }

        System.out.println("INQUIRY: well: ");
        Log.log(Log.INFO, "INQUIRY: well: ");
        System.out.println("well water level = " + well + "/" + WELl_MAX);
        Log.log(Log.INFO, "well water level = " + well + "/" + WELl_MAX);
        System.out.println("wellIsAvailable / !inUse = " + wellIsAvailable);
        Log.log(Log.INFO, "wellIsAvailable / !inUse = " + wellIsAvailable);
        System.out.println("wellTurn progress = " + wellTurns + "/" + WELL_MAX_TURNS);
        Log.log(Log.INFO, "wellTurn progress = " + wellTurns + "/" + WELL_MAX_TURNS);

        System.out.println("INQUIRY: domesticated = ");
        Log.log(Log.INFO, "INQUIRY: domesticated = ");
        for (AbstractDomesticatedAnimal domesticatedAnimal : domesticatedAnimalOnGround) {
            System.out.println(domesticatedAnimal.getType() + " " +
                    domesticatedAnimal.getHealth() + "% [" +
                    domesticatedAnimal.getX() + " " +
                    domesticatedAnimal.getY() + "]" );
            Log.log(Log.INFO, domesticatedAnimal.getType() + " " +
                                        domesticatedAnimal.getHealth() + "% [" +
                                        domesticatedAnimal.getX() + " " +
                                        domesticatedAnimal.getY() + "]" );
        }

        System.out.println("INQUIRY: wild = ");
        Log.log(Log.INFO, "INQUIRY: wild = ");
        for (AbstractWildAnimal wildAnimal : wildAnimalOnGround) {
            System.out.println(wildAnimal.getType() + " " +
                    wildAnimal.getCagesThrown() + "CT [" +
                    wildAnimal.getX() + " " +
                    wildAnimal.getY() + "]" );
            Log.log(Log.INFO, wildAnimal.getType() + " " +
                                        wildAnimal.getCagesThrown() + "CT [" +
                                        wildAnimal.getX() + " " +
                                        wildAnimal.getY() + "]" );
        }

        System.out.println("INQUIRY: pet = ");
        Log.log(Log.INFO, "INQUIRY: pet = ");
        for (AbstractPetAnimal petAnimal : petAnimalOnGround) {
            System.out.println(petAnimal.getType() + " [" +
                    petAnimal.getX() + " " +
                    petAnimal.getY() + "]" );
            Log.log(Log.INFO, petAnimal.getType() + " [" +
                                        petAnimal.getX() + " " +
                                        petAnimal.getY() + "]" );
        }

        System.out.println("INQUIRY: productsOnGround = ");
        Log.log(Log.INFO, "INQUIRY: productsOnGround = ");
        for (Product product : productOnGround) {
            System.out.println(product.getProductType() + " " +
                    product.getProductTurns() + "NT [" +
                    product.getX() + " " +
                    product.getY() + "]" );
            Log.log(Log.INFO, product.getProductType() + " " +
                                        product.getProductTurns() + "NT [" +
                                        product.getX() + " " +
                                        product.getY() + "]");
        }

        System.out.println("INQUIRY: storage info :");
        Log.log(Log.INFO, "INQUIRY: storage info :");

        System.out.println("INQUIRY: storage int = " + getStorage() + "/" + STORAGE_MAX);
        Log.log(Log.INFO, "INQUIRY: storage int = " + getStorage() + "/" + STORAGE_MAX);

        System.out.println("INQUIRY: in storage =");
        Log.log(Log.INFO, "INQUIRY: in storage =");
        for (Product product : storage) {
            System.out.println(product.getProductType());
            Log.log(Log.INFO, product.getProductType().toString());
        }


        System.out.println("INQUIRY: truck info: ");
        Log.log(Log.INFO, "INQUIRY: truck info: ");

        System.out.println("INQUIRY: truck int = " + getTruckStorage() + "/" + TRUCK_MAX);
        Log.log(Log.INFO, "INQUIRY: truck int = " + getTruckStorage() + "/" + TRUCK_MAX);

        System.out.println("truckIsAvailable / !inUse = " + truckIsAvailable);
        Log.log(Log.INFO, "truckIsAvailable / !inUse = " + truckIsAvailable);
        System.out.println("truckTurn progress = " + truckTurns + "/" + TRUCK_MAX_TURNS);
        Log.log(Log.INFO, "truckTurn progress = " + truckTurns + "/" + TRUCK_MAX_TURNS);
        System.out.println("truckTempMoney = " + truckTempMoney);
        Log.log(Log.INFO, "truckTempMoney = " + truckTempMoney);


        System.out.println("INQUIRY: in truck = ");
        Log.log(Log.INFO, "INQUIRY: in truck = ");
        for (Product product : truck) {
            System.out.println(product.getProductType());
            Log.log(Log.INFO, product.getProductType().toString());
        }

        System.out.println("INQUIRY: workshops: ");
        Log.log(Log.INFO, "INQUIRY: workshops:");

        System.out.println("flour bought? = " + workshopFlour);
        System.out.println("flour WS isAvailable =" + flour.isAvailable());
        System.out.println("flour WS progress = " + flour.getTurn() + "/" + flour.getMaxTurn());
        Log.log(Log.INFO, "flour bought? = " + workshopFlour);
        Log.log(Log.INFO, "flour WS isAvailable =" + flour.isAvailable());
        Log.log(Log.INFO, "flour WS progress = " + flour.getTurn() + "/" + flour.getMaxTurn());

        System.out.println("cloth WS bought? = " + workshopCloth);
        System.out.println("cloth WS isAvailable =" + cloth.isAvailable());
        System.out.println("cloth WS progress = " + cloth.getTurn() + "/" + cloth.getMaxTurn());
        Log.log(Log.INFO, "cloth WS bought? = " + workshopCloth);
        Log.log(Log.INFO, "cloth WS isAvailable =" + cloth.isAvailable());
        Log.log(Log.INFO, "cloth WS progress = " + cloth.getTurn() + "/" + cloth.getMaxTurn());

        System.out.println("packetmilk WS bought? = " + workshopPacketmilk);
        System.out.println("packetmilk WS isAvailable =" + packetmilk.isAvailable());
        System.out.println("packetmilk WS progress = " + packetmilk.getTurn() + "/" + packetmilk.getMaxTurn());
        Log.log(Log.INFO, "packetmilk WS bought? = " + workshopPacketmilk);
        Log.log(Log.INFO, "packetmilk WS isAvailable =" + packetmilk.isAvailable());
        Log.log(Log.INFO, "packetmilk WS progress = " + packetmilk.getTurn() + "/" + packetmilk.getMaxTurn());

        System.out.println("bread WS bought? = " + workshopBread);
        System.out.println("bread WS isAvailable =" + bread.isAvailable());
        System.out.println("bread WS progress = " + bread.getTurn() + "/" + bread.getMaxTurn());
        Log.log(Log.INFO, "bread WS bought? = " + workshopBread);
        Log.log(Log.INFO, "bread WS isAvailable =" + bread.isAvailable());
        Log.log(Log.INFO, "bread WS progress = " + bread.getTurn() + "/" + bread.getMaxTurn());

        System.out.println("shirt WS bought? = " + workshopShirt);
        System.out.println("shirt WS isAvailable =" + shirt.isAvailable());
        System.out.println("shirt WS progress = " + shirt.getTurn() + "/" + shirt.getMaxTurn());
        Log.log(Log.INFO, "shirt WS bought? = " + workshopShirt);
        Log.log(Log.INFO, "shirt WS isAvailable =" + shirt.isAvailable());
        Log.log(Log.INFO, "shirt WS progress = " + shirt.getTurn() + "/" + shirt.getMaxTurn());

        System.out.println("icecream WS bought? = " + workshopIcecream);
        System.out.println("icecream WS isAvailable =" + icecream.isAvailable());
        System.out.println("icecream WS progress = " + icecream.getTurn() + "/" + icecream.getMaxTurn());


        System.out.println("INQUIRY: coinTask = " + coinTaskProgression + "/" + coinTask);
        Log.log(Log.INFO, "INQUIRY: coinTask = " + coinTaskProgression + "/" + coinTask);

        if (eggProductTask == 0) {
            System.out.println("INQUIRY: no eggProductTask");
            Log.log(Log.INFO,"INQUIRY: no eggProductTask");
        }
        else {
            System.out.println("INQUIRY: eggProductTask = " + eggProductTaskProgression + "/" + eggProductTask);
            Log.log(Log.INFO, "INQUIRY: eggProductTask = " + eggProductTaskProgression + "/" + eggProductTask);
        }

        if (featherProductTask == 0) {
            System.out.println("INQUIRY: no featherProductTask");
            Log.log(Log.INFO, "INQUIRY: no featherProductTask");
        }
        else {
            System.out.println("INQUIRY: featherProductTask = " + featherProductTaskProgression + "/" + featherProductTask);
            Log.log(Log.INFO, "INQUIRY: featherProductTask = " + featherProductTaskProgression + "/" + featherProductTask);
        }

        if (milkProductTask == 0) {
            System.out.println("INQUIRY: no milkProductTask");
            Log.log(Log.INFO, "INQUIRY: no milkProductTask");
        }
        else {
            System.out.println("INQUIRY: milkProductTask = " + milkProductTaskProgression + "/" + milkProductTask);
            Log.log(Log.INFO, "INQUIRY: milkProductTask = " + milkProductTaskProgression + "/" + milkProductTask);
        }

        if (flourProductTask == 0) {
            System.out.println("INQUIRY: no flourProductTask");
            Log.log(Log.INFO, "INQUIRY: no flourProductTask");
        }
        else {
            System.out.println("INQUIRY: flourProductTask = " + flourProductTaskProgression + "/" + flourProductTask);
            Log.log(Log.INFO, "INQUIRY: flourProductTask = " + flourProductTaskProgression + "/" + flourProductTask);
        }

        if (clothProductTask == 0) {
            System.out.println("INQUIRY: no clothProductTask");
            Log.log(Log.INFO, "INQUIRY: no clothProductTask");
        }
        else {
            System.out.println("INQUIRY: clothProductTask = " + clothProductTaskProgression + "/" + clothProductTask);
            Log.log(Log.INFO, "INQUIRY: clothProductTask = " + clothProductTaskProgression + "/" + clothProductTask);
        }

        if (packetmilkProductTask == 0) {
            System.out.println("INQUIRY: no packetmilkProductTask");
            Log.log(Log.INFO, "INQUIRY: no packetmilkProductTask");
        }
        else {
            System.out.println("INQUIRY: packetmilkProductTask = " + packetmilkProductTaskProgression + "/" + packetmilkProductTask);
            Log.log(Log.INFO, "INQUIRY: packetmilkProductTask = " + packetmilkProductTaskProgression + "/" + packetmilkProductTask);
        }

        if (breadProductTask == 0) {
            System.out.println("INQUIRY: no breadProductTask");
            Log.log(Log.INFO, "INQUIRY: no breadProductTask");
        }
        else {
            System.out.println("INQUIRY: breadProductTask = " + breadProductTaskProgression + "/" + breadProductTask);
            Log.log(Log.INFO, "INQUIRY: breadProductTask = " + breadProductTaskProgression + "/" + breadProductTask);
        }

        if (shirtProductTask == 0) {
            System.out.println("INQUIRY: no shirtProductTask");
            Log.log(Log.INFO, "INQUIRY: no shirtProductTask");
        }
        else {
            System.out.println("INQUIRY: shirtProductTask = " + shirtProductTaskProgression + "/" + shirtProductTask);
            Log.log(Log.INFO, "INQUIRY: shirtProductTask = " + shirtProductTaskProgression + "/" + shirtProductTask);
        }

        if (icecreamProductTask == 0) {
            System.out.println("INQUIRY: no icecreamProductTask");
            Log.log(Log.INFO, "INQUIRY: no icecreamProductTask");
        }
        else {
            System.out.println("INQUIRY: shirtProductTask = " + icecreamProductTaskProgression + "/" + icecreamProductTask);
            Log.log(Log.INFO, "INQUIRY: shirtProductTask = " + icecreamProductTaskProgression + "/" + icecreamProductTask);
        }

        if (chickenTasks == 0) {
            System.out.println("INQUIRY: no chickenTasks");
            Log.log(Log.INFO, "INQUIRY: no chickenTasks");
        }
        else {
            System.out.println("INQUIRY: chickenTasks = " + chickenTaskProgression + "/" + chickenTasks);
            Log.log(Log.INFO, "INQUIRY: chickenTasks = " + chickenTaskProgression + "/" + chickenTasks);
        }

        if (turkeyTasks == 0) {
            System.out.println("INQUIRY: no turkeyTasks");
            Log.log(Log.INFO, "INQUIRY: no turkeyTasks");
        }
        else {
            System.out.println("INQUIRY: turkeyTasks = " + turkeyTaskProgression + "/" + turkeyTasks);
            Log.log(Log.INFO, "INQUIRY: turkeyTasks = " + turkeyTaskProgression + "/" + turkeyTasks);
        }

        if (buffaloTasks == 0) {
            System.out.println("INQUIRY: no buffaloTasks");
            Log.log(Log.INFO, "INQUIRY: no buffaloTasks");
        }
        else {
            System.out.println("INQUIRY: buffaloTasks = " + buffaloTaskProgression + "/" + buffaloTasks);
            Log.log(Log.INFO, "INQUIRY: buffaloTasks = " + buffaloTaskProgression + "/" + buffaloTasks);
        }


        System.out.println("Xx\tinquiry done\txX");
        Log.log(Log.INFO, "Xx\tinquiry done\txX");
    }

    public boolean isFinished() {
        coinTaskBoolean =  coinTaskBoolean || (coinTaskProgression >= coinTask);

        productTasksBoolean = productTasksBoolean ||
                (eggProductTaskProgression >= eggProductTask) ||
                (featherProductTaskProgression >= featherProductTask) ||
                (milkProductTaskProgression >= milkProductTask) ||
                (flourProductTaskProgression >= flourProductTask) ||
                (clothProductTaskProgression >= clothProductTask) ||
                (packetmilkProductTaskProgression >= packetmilkProductTask) ||
                (breadProductTaskProgression >= breadProductTask) ||
                (shirtProductTaskProgression >= shirtProductTask) ||
                (icecreamProductTaskProgression >= icecreamProductTask);

        animalTasksBoolean = animalTasksBoolean ||
                (chickenTaskProgression >= chickenTasks) ||
                (turkeyTaskProgression >= turkeyTasks) ||
                (buffaloTaskProgression >= buffaloTasks);



        boolean allTasksBoolean = coinTaskBoolean && productTasksBoolean && animalTasksBoolean;
        return allTasksBoolean;
    }

    public boolean isPrized() { return turn <= maxTime; }

    public int getReward() { return reward; }
}
