import controller.LevelManager;
import log.Log;
import model.animals.AnimalTypes;
import model.level.Level;
import model.products.ProductTypes;
import view.LevelInputProcessor;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class anotherMain {
    public static void main(String args[]) {
        Log.log(Log.INFO, "Game Main Run");

        Scanner scanner = new Scanner(System.in);
        LevelManager levelManager = new LevelManager(1, 0);
        LevelInputProcessor levelInputProcessor = new LevelInputProcessor(levelManager, scanner);
        System.out.println(levelInputProcessor.run());
    }

    static void initialization() {
        Log.log(Log.INFO, "initialization and loading");
        initializationOfLevels();
    }

    static void initializationOfLevels() {
        Log.log(Log.INFO, "initializationOfLevels: loading levels from mission.txt");
        File levelFile = new File("missions.txt");
        try {
            Scanner scanner = new Scanner(levelFile);

            String line1 = scanner.nextLine(); // first line
            Level.setTotalLevels(  Integer.parseInt( line1.split("\\s")[1] )  ); // total levels
            //System.out.println(Level.getTotalLevels());

            Level[] levels = new Level[Level.getTotalLevels()]; // creating for levels
            for (int i = 0; i < Level.getTotalLevels(); i++) {
                scanner.nextLine(); // level no

                int initialCoins = Integer.parseInt( scanner.nextLine().split("\\s")[1] );
                //System.out.println(initialCoins);
                int coinTask = Integer.parseInt( scanner.nextLine().split("\\s")[1] );
                //System.out.println(coinTask);

                String productString;
                HashMap<ProductTypes, Integer> productTasks = new HashMap<>();
                productString = scanner.nextLine();
                if ( Integer.parseInt(productString.split("\\s")[1]) != 0 )
                    productTasks.put(ProductTypes.EGG, Integer.parseInt(productString.split("\\s")[1]));
                productString = scanner.nextLine();
                if ( Integer.parseInt(productString.split("\\s")[1]) != 0 )
                    productTasks.put(ProductTypes.FEATHER, Integer.parseInt(productString.split("\\s")[1]));
                productString = scanner.nextLine();
                if ( Integer.parseInt(productString.split("\\s")[1]) != 0 )
                    productTasks.put(ProductTypes.MILK, Integer.parseInt(productString.split("\\s")[1]));
                productString = scanner.nextLine();
                if ( Integer.parseInt(productString.split("\\s")[1]) != 0 )
                    productTasks.put(ProductTypes.FLOUR, Integer.parseInt(productString.split("\\s")[1]));
                productString = scanner.nextLine();
                if ( Integer.parseInt(productString.split("\\s")[1]) != 0 )
                    productTasks.put(ProductTypes.CLOTH, Integer.parseInt(productString.split("\\s")[1]));
                productString = scanner.nextLine();
                if ( Integer.parseInt(productString.split("\\s")[1]) != 0 )
                    productTasks.put(ProductTypes.PACKETMILK, Integer.parseInt(productString.split("\\s")[1]));
                productString = scanner.nextLine();
                if ( Integer.parseInt(productString.split("\\s")[1]) != 0 )
                    productTasks.put(ProductTypes.BREAD, Integer.parseInt(productString.split("\\s")[1]));
                productString = scanner.nextLine();
                if ( Integer.parseInt(productString.split("\\s")[1]) != 0 )
                    productTasks.put(ProductTypes.SHIRT, Integer.parseInt(productString.split("\\s")[1]));
                productString = scanner.nextLine();
                if ( Integer.parseInt(productString.split("\\s")[1]) != 0 )
                    productTasks.put(ProductTypes.ICECREAM, Integer.parseInt(productString.split("\\s")[1]));
                //System.out.println(productTasks);

                String animalString;
                HashMap <AnimalTypes, Integer> animalTasks = new HashMap<>();
                animalString = scanner.nextLine();
                if ( Integer.parseInt(animalString.split("\\s")[1]) != 0 )
                    animalTasks.put(AnimalTypes.CHICKEN, Integer.parseInt(animalString.split("\\s")[1]));
                animalString = scanner.nextLine();
                if ( Integer.parseInt(animalString.split("\\s")[1]) != 0 )
                    animalTasks.put(AnimalTypes.TURKEY, Integer.parseInt(animalString.split("\\s")[1]));
                animalString = scanner.nextLine();
                if ( Integer.parseInt(animalString.split("\\s")[1]) != 0 )
                    animalTasks.put(AnimalTypes.BUFFALO, Integer.parseInt(animalString.split("\\s")[1]));
                //System.out.println(animalTasks);

                String spawnString;
                String[] spawnStringSplit;
                HashMap <Integer, AnimalTypes> spawn = new HashMap<>();
                spawnString = scanner.nextLine();
                spawnStringSplit = spawnString.split("\\s");
                if ( Integer.parseInt(spawnStringSplit[1]) != 0 ) {
                    for (int j = 0; j < Integer.parseInt(spawnStringSplit[1]); j++)
                        spawn.put(Integer.parseInt(spawnStringSplit[2+j]), AnimalTypes.LION);
                }
                spawnString = scanner.nextLine();
                spawnStringSplit = spawnString.split("\\s");
                if ( Integer.parseInt(spawnStringSplit[1]) != 0 ) {
                    for (int j = 0; j < Integer.parseInt(spawnStringSplit[1]); j++)
                        spawn.put(Integer.parseInt(spawnStringSplit[2+j]), AnimalTypes.BEAR);
                }
                spawnString = scanner.nextLine();
                spawnStringSplit = spawnString.split("\\s");
                if ( Integer.parseInt(spawnStringSplit[1]) != 0 ) {
                    for (int j = 0; j < Integer.parseInt(spawnStringSplit[1]); j++)
                        spawn.put(Integer.parseInt(spawnStringSplit[2+j]), AnimalTypes.TIGER);
                }
                //System.out.println(spawn);

                int maxTurn = Integer.parseInt( scanner.nextLine().split("\\s")[1] );
                //System.out.println(maxTurn);
                int prize = Integer.parseInt( scanner.nextLine().split("\\s")[1] );
                //System.out.println(prize);

                Level level = new Level(initialCoins,
                        spawn,
                        maxTurn,
                        prize,
                        coinTask,
                        productTasks,
                        animalTasks);
                levels[i] = level;
            }
            Level.setLevels(levels);
            Log.log(Log.INFO, "initializationOfLevels successfully");
            scanner.close();
        } catch (Exception e) {
            Log.log(Log.EXCEPTION, "exception catch in initializationOfLevels");
            e.printStackTrace();
        }
    }

    static void anotherlate() {
        ArrayList <Blah> blahList = new ArrayList<>();
        Blah n1 = new Blah(2, "funf");
        Blah n2 = new Blah(1, "not");
        Blah n3 = new Blah(0, "nullino");
        blahList.add(n1);
        blahList.add(n3);
        blahList.add(n2);
        Collections.sort(blahList);
        for (Blah blah : blahList) {
            System.out.println(blah.getA() + " " + blah.getB());
        }
        ArrayList <Blah> blahList2 = new ArrayList<>();
        //blahList2.add(n1);
        //blahList2.add(n3);
        //blahList2.add(n2);
        blahList.removeAll(blahList2);
        for (Blah blah : blahList) {
            System.out.println(blah.getA() + " " + blah.getB());
        }
        System.out.println(blahList.isEmpty());
        for (int i = 1; i <= 10; i++) {
            System.out.println(i);
        }
    }
}

class Blah implements Comparable<Blah>{
    int a;
    String b;
    public Blah (int a, String b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }
    public String getB() {
        return b;
    }

    @Override
    public int compareTo(Blah o) {
        if(o.getA() > a) {
            return -1;
        }
        else if(o.getA() < a) {
            return 1;
        }
        return 0;
    }
}
