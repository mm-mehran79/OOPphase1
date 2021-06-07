package model.animals.wild;

import model.animals.AnimalTypes;

public class Lion extends AbstractWildAnimal {

    // constructor
    public Lion(int spawnTurn) {
        super(AnimalTypes.LION, spawnTurn);
    }

    // cage implementation for player
    @Override
    public boolean cage() {
        if ( isTrapped && !isCaged && cagesThrown < 3 )
            cagesThrown += 2;
        if ( cagesThrown == 3 )
            isCaged = true;
        return isCaged;
    }
}
