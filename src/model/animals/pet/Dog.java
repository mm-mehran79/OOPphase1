package model.animals.pet;

import model.animals.AnimalTypes;

public class Dog extends AbstractPetAnimal {
    static public int PRICE = 100; // Dog's price

    // constructor
    public Dog(AnimalTypes animalType) {
        super(AnimalTypes.DOG);
    }
}
