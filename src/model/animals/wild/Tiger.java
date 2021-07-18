package model.animals.wild;

import model.animals.AnimalTypes;

public class Tiger extends AbstractWildAnimal {

    // constructor
    public Tiger(int spawnTurn) {
        super(AnimalTypes.TIGER, spawnTurn);
    }

    // cage implementation for player
    @Override
    public boolean cage() {
        if ( isTrapped && !isCaged && cagesThrown < 4 )
            cagesThrown += 2;
        if ( cagesThrown >= 4 )
            isCaged = true;
        return isCaged;
    }
}
