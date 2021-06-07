package model.animals.domesticated;

import model.products.Product;
import model.animals.AnimalTypes;
import model.products.ProductTypes;

public class Buffalo extends AbstractDomesticatedAnimal {
    static public int PRICE = 400; // Buffalo's price

    // constructor
    Buffalo() {
        super(AnimalTypes.BUFFALO); // parent's constructor
    }

    // product giving implementation
    @Override
    public Product giveProduct() {
        if ( animalsTurns != 0 && animalsTurns % 5 == 0) {
            return new Product(ProductTypes.MILK);
        }
        return null;
    }
}
