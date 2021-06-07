package model.animals.pet;

import model.animals.AbstractAnimal;
import model.animals.AnimalTypes;

public abstract class AbstractPetAnimal extends AbstractAnimal {
    boolean isAlive; // is it alive


    // constructor
    public AbstractPetAnimal(AnimalTypes animalType) {
        super(animalType);
        isAlive = true;
    }

    // killing the pet animal
    public boolean kill() {
        isAlive = false;
        return isAlive;
    }

    // isAlive getter
    public boolean isAlive() {
        return isAlive;
    }
}
