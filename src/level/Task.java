package level;

import model.animals.AnimalTypes;
import model.products.ProductTypes;

import java.util.HashMap;

public class Task {
    // properties
    int coinTask;
    HashMap <ProductTypes, Integer> productTasks;
    HashMap <AnimalTypes, Integer> animalTasks;

    // constructor
    public Task(int coinTask, HashMap<ProductTypes, Integer> productTasks, HashMap<AnimalTypes, Integer> animalTasks) {
        this.coinTask = coinTask;
        this.productTasks = productTasks;
        this.animalTasks = animalTasks;
    }

    // CoinTask getter
    public int getCoinTask() {
        return coinTask;
    }

    // ProductTasks getter
    public HashMap<ProductTypes, Integer> getProductTasks() {
        return productTasks;
    }

    // AnimalTasks getter
    public HashMap<AnimalTypes, Integer> getAnimalTasks() {
        return animalTasks;
    }
}
