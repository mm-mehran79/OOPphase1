package model.products;

public class Product{
    // static ints
    // prices
    public static int EGG_PRICE = 15;
    public static int FEATHER_PRICE = 20;
    public static int MILK_PRICE = 25;
    public static int FLOUR_PRICE = 40;
    public static int CLOTH_PRICE = 50;
    public static int PACKETMILK_PRICE = 60;
    public static int BREAD_PRICE = 80;
    public static int SHIRT_PRICE = 100;
    public static int ICECREAM_PRICE = 120;
    public static int LION_PRICE = 300;
    public static int BEAR_PRICE = 400;
    public static int TIGER_PRICE = 500;
    // storage
    public static int PRIMARY_STORAGE = 1;
    public static int SECONDARY_STORAGE = 2;
    public static int TERTIARY_STORAGE = 4;
    public static int ANIMAL_STORAGE = 15;
    // max turns
    public static int PRIMARY_MAX_TURNS = 4;
    public static int SECONDARY_MAX_TURNS = 5;
    public static int TERTIARY_MAX_TURNS = 6;
    public static int ANIMAL_MAX_TURNS = 5;

    // private properties
    private ProductTypes productType;
    private int price;
    private int storage;
    private int productTurns;
    private int maxTurns;

    public Product(ProductTypes productType) {
        this.productType = productType;
        productTurns = 0;
        if (this.productType == ProductTypes.EGG) {
            price = EGG_PRICE;
            storage = PRIMARY_STORAGE;
            maxTurns = PRIMARY_MAX_TURNS;
        }
        else if (this.productType == ProductTypes.FEATHER) {
            price = FEATHER_PRICE;
            storage = PRIMARY_STORAGE;
            maxTurns = PRIMARY_MAX_TURNS;
        }
        else if (this.productType == ProductTypes.MILK) {
            price = MILK_PRICE;
            storage = PRIMARY_STORAGE;
            maxTurns = PRIMARY_MAX_TURNS;
        }
        else if (this.productType == ProductTypes.FLOUR) {
            price = FLOUR_PRICE;
            storage = SECONDARY_STORAGE;
            maxTurns = SECONDARY_MAX_TURNS;
        }
        else if (this.productType == ProductTypes.CLOTH) {
            price = CLOTH_PRICE;
            storage = SECONDARY_STORAGE;
            maxTurns = SECONDARY_MAX_TURNS;
        }
        else if (this.productType == ProductTypes.PACKETMILK) {
            price = PACKETMILK_PRICE;
            storage = SECONDARY_STORAGE;
            maxTurns = SECONDARY_MAX_TURNS;
        }
        else if (this.productType == ProductTypes.BREAD) {
            price = BREAD_PRICE;
            storage = TERTIARY_STORAGE;
            maxTurns = TERTIARY_MAX_TURNS;
        }
        else if (this.productType == ProductTypes.SHIRT) {
            price = SHIRT_PRICE;
            storage = TERTIARY_STORAGE;
            maxTurns = TERTIARY_MAX_TURNS;
        }
        else if (this.productType == ProductTypes.ICECREAM) {
            price = ICECREAM_PRICE;
            storage = TERTIARY_STORAGE;
            maxTurns = TERTIARY_MAX_TURNS;
        }
        else if (this.productType == ProductTypes.LION) {
            price = LION_PRICE;
            storage = ANIMAL_STORAGE;
            maxTurns = ANIMAL_MAX_TURNS;
        }
        else if (this.productType == ProductTypes.BEAR) {
            price = BEAR_PRICE;
            storage = ANIMAL_STORAGE;
            maxTurns = ANIMAL_MAX_TURNS;
        }
        else if (this.productType == ProductTypes.TIGER) {
            price = TIGER_PRICE;
            storage = ANIMAL_STORAGE;
            maxTurns = ANIMAL_MAX_TURNS;
        }
    }

    // ProductType getter
    public ProductTypes getProductType() {
        return productType;
    }

    // Price getter
    public int getPrice() {
        return price;
    }

    // Storage getter
    public int getStorage() {
        return storage;
    }

    // ProductTurns getter
    public int getProductTurns() {
        return productTurns;
    }

    // maxTurns getter
    public int getMaxTurns() {
        return maxTurns;
    }

}
