package model.workshop;

import model.products.ProductTypes;

public class Workshop {
    public static int TURN_FLOUR = 4;
    public static int TURN_CLOTH = 5;
    public static int TURN_PACKETMILK = 6;
    public static int TURN_BREAD = 5;
    public static int TURN_SHIRT = 6;
    public static int TURN_ICECREAM = 7;
    boolean isAvailable = true;
    ProductTypes productTypeOutput;
    ProductTypes productTypeInput;
    int turn = 0;
    int maxTurn;

    public Workshop(ProductTypes productTypeOutput) {
        isAvailable = true;
        turn = 0;
        if (productTypeOutput == ProductTypes.FLOUR) {
            this.productTypeOutput = ProductTypes.FLOUR;
            this.productTypeInput = ProductTypes.EGG;
            this.maxTurn = TURN_FLOUR;
        }
        else if (productTypeOutput == ProductTypes.CLOTH) {
            this.productTypeOutput = ProductTypes.CLOTH;
            this.productTypeInput = ProductTypes.FEATHER;
            this.maxTurn = TURN_CLOTH;
        }
        else if (productTypeOutput == ProductTypes.PACKETMILK) {
            this.productTypeOutput = ProductTypes.PACKETMILK;
            this.productTypeInput = ProductTypes.MILK;
            this.maxTurn = TURN_PACKETMILK;
        }
        else if (productTypeOutput == ProductTypes.BREAD) {
            this.productTypeOutput = ProductTypes.BREAD;
            this.productTypeInput = ProductTypes.FLOUR;
            this.maxTurn = TURN_BREAD;
        }
        else if (productTypeOutput == ProductTypes.SHIRT) {
            this.productTypeOutput = ProductTypes.SHIRT;
            this.productTypeInput = ProductTypes.CLOTH;
            this.maxTurn = TURN_SHIRT;
        }
        else if (productTypeOutput == ProductTypes.ICECREAM) {
            this.productTypeOutput = ProductTypes.ICECREAM;
            this.productTypeInput = ProductTypes.PACKETMILK;
            this.maxTurn = TURN_ICECREAM;
        }
        else {
            this.productTypeOutput = null;
            this.productTypeInput = null;
            this.maxTurn = 0;
        }
    }

    public void addTurn() {
        turn++;
    }

    public boolean make() {
        if (isAvailable) {
            isAvailable = false;
            turn = 0;
            return true;
        }
        return false;
    }

    public boolean getProduct() {
        return !isAvailable && (turn >= maxTurn);
    }

    public boolean setZero() {
        if(getProduct()) {
            turn = 0;
            return true;
        }
        return false;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getTurn() {
        return turn;
    }

    public int getMaxTurn() {
        return maxTurn;
    }

    public ProductTypes getProductTypeOutput() {
        return productTypeOutput;
    }
}
