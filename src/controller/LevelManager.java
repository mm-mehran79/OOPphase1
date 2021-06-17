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

    public LevelManager(Level level) {
        this.level = level;

    }
}
