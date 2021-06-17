import log.Log;
import model.animals.AnimalTypes;
import model.level.Level;
import model.products.ProductTypes;

import java.io.*;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner; // Import the Scanner class to read text files


public class Main {
    public static void main(String[] args) {
        Log.log(Log.INFO, "Game Main Ran");
        initialization();

        Log.close();
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
                HashMap <ProductTypes, Integer> productTasks = new HashMap<>();
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
        } catch (Exception e) {
            Log.log(Log.EXCEPTION, "exception catch in initializationOfLevels");
            e.printStackTrace();
        }
    }

    static void late() {
        File myFileBlah = new File("1.txt");
        Scanner scanner;

        {
            try {
                scanner = new Scanner(myFileBlah);
                while (scanner.hasNextLine())
                    System.out.println(scanner.nextLine());
                scanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("meme");
                System.out.println(e);
                e.printStackTrace();
                System.out.println("meme");
            }
        }

        File myMatrix = new File("matrix.txt");

        {
            try {
                scanner = new Scanner(myMatrix);
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 3; j++)
                        System.out.print(scanner.nextFloat() + "\t" + "\t");
                    System.out.println();
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("meme");
                System.out.println(e);
                e.printStackTrace();
                System.out.println("meme");
            }
        }

        File myFileOutPutter = new File("myFileOutPutter.txt");
        try {
            FileWriter myFileOutPutterWriter = new FileWriter(myFileOutPutter);
            myFileOutPutterWriter.write("\n");
            myFileOutPutterWriter.write("HIPPI");
            myFileOutPutterWriter.write("\n");
            myFileOutPutterWriter.write("BITTI");
            myFileOutPutterWriter.write("\n");
            myFileOutPutterWriter.write("hi");
            myFileOutPutterWriter.write("\n");
            System.out.println("myFileOutPutter.txt");
            myFileOutPutterWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            scanner = new Scanner(myFileOutPutter);
            while (scanner.hasNextLine())
                System.out.println(scanner.nextLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            FileWriter myFileOutPutterWriter = new FileWriter(myFileOutPutter, true);
            System.out.println("append");
            myFileOutPutterWriter.write("tock talk");
            myFileOutPutterWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("LOG");
        Log.log(Log.ERROR,"ali is dumb");
        Log.log(Log.INFO, "ali is dumbdumb");

        File defErrorFile = new File("q334ovwntn7wtn784yvn9.txt");
        try {
            scanner = new Scanner(defErrorFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.log(e);
        }

        Log.close();

        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            System.out.println(random.nextInt(7));
        }


        /*File file = new File("filename.txt");

        int tmp = 0;
        //create a file
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        }
        catch (IOException e) {
            System.out.println("An error occurred 1.");
            e.printStackTrace();
            tmp++;
        }
        //System.out.println("After catch!" + tmp);

        //Scanner: read a file
       *//*try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextInt()) {

                //String data = scanner.nextLine();
                int data = scanner.nextInt();
                System.out.println(data);

               //for (int i = 0; i < 4; i++) {
               //    for (int j = 0; j < 3; j++) {
               //        int data = scanner.nextInt();
               //        System.out.print(" " + data);
               //    }
               //    System.out.println();
               //}
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred 2.");
            //e.printStackTrace();
        }*//*

        //FileWriter & FileReader
        try{
            // FileWriter:
            FileWriter fileWriter = new FileWriter(file);
            // Writes the content to the file
            fileWriter.write("This\n is\n an\n example\n");
            fileWriter.flush();
            System.out.println("\nfw");

            // BufferedWriter:
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("\nThis\n is\n another\n example\n");
            bufferedWriter.flush();
            bufferedWriter.close();
            fileWriter.close();
            System.out.println("\nbw");

            // FileReader:
            FileReader fileReader = new FileReader(file);
            *//*char [] a = new char[10]; //change this value!!
            fileReader.read(a);   // reads the content to the array
            // Reads the content from file
            for(char c : a)
                System.out.print(c);   // prints the characters one by one
            System.out.println("\nfr");*//*

            // BufferedReader:
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            line = bufferedReader.readLine();
            while (line != null) {
                System.out.println(line);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            fileReader.close();
            System.out.println("\nbr");
        }
        catch (IOException e) {
            System.out.println("An error occurred 3.");
            e.printStackTrace();
        }

        //delete a file
        if (file.delete()) {
            System.out.println("Deleted the file: " + file.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }

        //Binary files
        try{
            File file2 = new File("filename2.txt");
            file2.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
            for (int i=0; i<100; i++)
                dataOutputStream.writeDouble((double) (1.0 * (i%10)));
            dataOutputStream.flush();
            dataOutputStream.close();
            fileOutputStream.close();

            FileInputStream fileInputStream = new FileInputStream(file2);
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);
            while (dataInputStream.available()>0) {
                double x = dataInputStream.readDouble();
                System.out.println(x);
            }
            dataInputStream.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred 3.");
            e.printStackTrace();
        }*/
    }
}
