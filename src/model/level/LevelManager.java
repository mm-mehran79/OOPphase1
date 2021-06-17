package model.level;

import model.animals.domesticated.AbstractDomesticatedAnimal;
import model.animals.pet.AbstractPetAnimal;
import model.animals.wild.AbstractWildAnimal;
import model.products.Product;

import java.util.ArrayList;

public class LevelManager {
    Level level;
    int coins;
    ArrayList <AbstractDomesticatedAnimal> domesticatedAnimals;
    ArrayList <AbstractPetAnimal> petAnimals;
    ArrayList <AbstractWildAnimal> wildAnimals;
    ArrayList <Product> products;

}
