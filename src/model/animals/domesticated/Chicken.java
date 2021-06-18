package model.animals.domesticated;

import model.products.Product;
import model.animals.AnimalTypes;
import model.products.ProductTypes;

public class Chicken extends AbstractDomesticatedAnimal {
    static public int PRICE = 100; // Chicken's price

    // constructor
    public Chicken() {
        super(AnimalTypes.CHICKEN); // parent's constructor
    }

    // product giving implementation
    @Override
    public Product giveProduct() {
        if ( animalsTurns != 0 && animalsTurns % 2 == 0) {
            return new Product(ProductTypes.EGG);
        }
        return null;
    }
}
