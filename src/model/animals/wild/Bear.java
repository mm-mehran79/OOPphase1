package model.animals.wild;

import model.animals.AnimalTypes;

public class Bear extends AbstractWildAnimal {

    // constructor
    public Bear(int spawnTurn) {
        super(AnimalTypes.BEAR, spawnTurn);
    }

    // cage implementation for player
    @Override
    public boolean cage() {
        if ( /*isTrapped &&*/ !isCaged && cagesThrown < 4 )
            cagesThrown += 2;
        if ( cagesThrown >= 4 )
            isCaged = true;
        return isCaged;
    }
}
