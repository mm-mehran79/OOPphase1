package model.animals.domesticated;

import model.products.Product;
import model.animals.AbstractAnimal;
import model.animals.AnimalTypes;

public abstract class AbstractDomesticatedAnimal extends AbstractAnimal {
    static final int MAX_HEALTH = 100; // max domesticated health
    int health; // health
    boolean isAlive; // is it alive

    // constructor
    public AbstractDomesticatedAnimal(AnimalTypes animalType) {
        super(animalType); // parent's constructor
        health = MAX_HEALTH; // initial health
        isAlive = true; // initial living status
    }

    // reducing the hunger and getting the living status
    public boolean hunger() {
        health -= 10;
        if (health  == 0)
            isAlive = false;
        return isAlive;
    }

    // eating grass
    public void eat(int[][] grass) {
        if ( grass[x][y] > 0 && health <= 50) {
            grass[x][y]--;
            health = MAX_HEALTH;
        }
    }

    // getting product from the domesticated animal;
    public abstract Product giveProduct();

    // killing the domesticated animal
    public boolean kill() {
        health = 0;
        isAlive = false;
        return isAlive;
    }

    // isAlive getter
    public boolean isAlive() {
        return isAlive;
    }
}
