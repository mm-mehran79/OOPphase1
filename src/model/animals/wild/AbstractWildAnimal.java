package model.animals.wild;

import model.animals.AbstractAnimal;
import model.animals.AnimalTypes;

public abstract class AbstractWildAnimal extends AbstractAnimal {
    public int cagesThrown; // number of cages that the wild animal is in
    public boolean isTrapped; // is the animal trapped? true if cagesThrown != 0
    public boolean isCaged; // is the animal completely caged? (like isAlive)
    public int spawnTurn; // the number of turns that it takes to spawn
    public boolean isSpawned = false; // is the animal spawned

    // constructor
    public AbstractWildAnimal(AnimalTypes animalType, int  spawnTurn) {
        super(animalType); // parent's constructor
        cagesThrown = 0; // initial cages thrown at animal
        isCaged = false; // initial cage status
        isTrapped = false; // initial trap status
        this.spawnTurn = spawnTurn; // initial and default spawnTurn
    }

    // overridden turnPlus
    @Override
    public void turnPlus() {
        if ( isTrapped && !isCaged && cagesThrown != 0 )
            cagesThrown--; // if the number of cages isn't 0 and other conditions, reduce the number of cagesThrown
        super.turnPlus();
    }

    // cage method for player
    public abstract boolean cage();

    // spawned, for when we spawn the animal
    public void wildSpawn() {
        isSpawned = true; // set true when we spawn
    }

    // get CagesThrown to the wild animal
    public int getCagesThrown() {
        return cagesThrown;
    }

    // is the wild animal trapped
    public boolean isTrapped() {
        return isTrapped;
    }

    // is the wild animal completely caged
    public boolean isCaged() {
        return isCaged;
    }

    // the wild animal's spawn turn
    public int getSpawnTurn() {
        return spawnTurn;
    }

    // has the wild animal spawned
    public boolean isSpawned() {
        return isSpawned;
    }
}
