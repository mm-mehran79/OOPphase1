package model.animals.domesticated;

import model.products.Product;
import model.animals.AnimalTypes;
import model.products.ProductTypes;

public class Turkey extends AbstractDomesticatedAnimal {
    static public int PRICE = 200; // Turkey's price

    // constructor
    Turkey() {
        super(AnimalTypes.TURKEY); // parent's constructor
    }

    // product giving implementation
    @Override
    public Product giveProduct() {
        if ( animalsTurns != 0 && animalsTurns % 3 == 0) {
            return new Product(ProductTypes.FEATHER);
        }
        return null;
    }
}
