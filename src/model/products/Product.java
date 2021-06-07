package model.products;

public class Product{
    private ProductTypes productType;
    private int price;
    private int storage;
    private int productTurns;

    public Product(ProductTypes productType) {
        this.productType = productType;
        productTurns = 0;
        if (this.productType == ProductTypes.EGG) {
            price = 15;
            storage = 1;
        }
        else if (this.productType == ProductTypes.FEATHER) {
            price = 20;
            storage = 1;
        }
        else if (this.productType == ProductTypes.MILK) {
            price = 25;
            storage = 1;
        }
        else if (this.productType == ProductTypes.FLOUR) {
            price = 40;
            storage = 1;
        }
        else if (this.productType == ProductTypes.CLOTH) {
            price = 50;
            storage = 1;
        }
        else if (this.productType == ProductTypes.PACKETMILK) {
            price = 60;
            storage = 1;
        }
    }
}
