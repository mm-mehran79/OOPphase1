package model.animals.pet;

import model.animals.AnimalTypes;

public class Dog extends AbstractPetAnimal {
    static public int PRICE = 100; // Dog's price

    // constructor
    public Dog() {
        super(AnimalTypes.DOG);
    }
}
