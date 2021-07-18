package model.animals.domesticated;

import log.Log;
import model.products.Product;
import model.animals.AbstractAnimal;
import model.animals.AnimalTypes;

public abstract class AbstractDomesticatedAnimal extends AbstractAnimal implements Comparable<AbstractDomesticatedAnimal>{
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
            System.out.println("levelManager: " + animalType.toString() + " @ [" + getX() + " " + getY() + "] ate grass");
            Log.log(Log.INFO, animalType.toString() + " @ [" + getX() + " " + getY() + "] ate grass");
            return;
        }
        System.err.println("levelManager: " + animalType.toString() + " @ [" + getX() + " " + getY() + "] didn't get grass");
        Log.log(Log.ERROR, animalType.toString() + " @ [" + getX() + " " + getY() + "] didn't get grass");
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

    // health getter
    public int getHealth() {
        return health;
    }

    @Override
    public int compareTo(AbstractDomesticatedAnimal o) {
        if (o.getHealth() > health)
            return -1;
        else if (o.getHealth() < health)
            return 1;
        return 0;
    }
}
