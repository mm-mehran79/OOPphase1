package controller;

import model.animals.domesticated.AbstractDomesticatedAnimal;
import model.animals.pet.AbstractPetAnimal;
import model.animals.wild.AbstractWildAnimal;
import model.level.Level;
import model.products.Product;

import java.util.ArrayList;

public class LevelManager {
    // level manager initialization properties
    Level level;
    int coins;
    ArrayList <AbstractDomesticatedAnimal> domesticatedAnimals;
    ArrayList <AbstractPetAnimal> petAnimals;
    ArrayList <AbstractWildAnimal> wildAnimals;
    ArrayList <Product> products;

    ArrayList <String> instructionQueue;
    // task booleans
    boolean coinTaskBoolean = false;
    boolean productTasksBoolean = false;
    boolean animalTasksBoolean = false;

    public LevelManager(Level level) {
        this.level = level;
        instructionQueue = new ArrayList<>();
    }

    public boolean addInstruction(String instruction) {
        instructionQueue.add(instruction);
        return true;
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
