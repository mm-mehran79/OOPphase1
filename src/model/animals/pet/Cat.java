package model.animals.pet;

import model.animals.AnimalTypes;

public class Cat extends AbstractPetAnimal {
    static public int PRICE = 150; // Cat's price

    // constructor
    public Cat(AnimalTypes animalType) {
        super(AnimalTypes.CAT);
    }
}
