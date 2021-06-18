package model.animals;

import java.util.Random;

public abstract class AbstractAnimal {
    public AnimalTypes animalType; // animal type
    public int x, y; // coordination
    public int animalsTurns; // the number of animals turns
    public static Random random; // random for move

    // constructor
    public AbstractAnimal(AnimalTypes animalType) {
        this.animalType = animalType; // setting the general type
        random = new Random();
        x = random.nextInt(6) + 1; // spawning x
        y = random.nextInt(6) + 1; // spawning x
        animalsTurns = 0; // number of turns that animal has been in the game
    }

    // move command
    public void move() {
        boolean moveBoolean = false;
        while ( !moveBoolean ) {
            int moveInt = random.nextInt(4);
            if ( moveInt == 0 && y != 1) {
                y--;
                moveBoolean = true;
            }
            else if ( moveInt == 1 && y != 6) {
                y++;
                moveBoolean = true;
            }
            else if ( moveInt == 2 && x != 1) {
                x--;
                moveBoolean = true;
            }
            else if ( moveInt == 3 && x != 6) {
                x++;
                moveBoolean = true;
            }
        }
    }

    // getting the type String
    public AnimalTypes getType() {
        return animalType;
    }

    // adding to the numbers of turns
    public void turnPlus() {
        animalsTurns++;
    }

    // get x cords
    public int getX() {
        return x;
    }

    // get y cords
    public int getY() {
        return y;
    }

    // get animalsTurns in game
    public int getAnimalsTurns() {
        return animalsTurns;
    }
}
