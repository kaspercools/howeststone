package org.howeststone.game.card;

import org.howeststone.game.card.state.CardState;
import org.howeststone.game.contract.CanCharge;
import org.howeststone.game.contract.Creature;

public class Spell extends BaseCard implements CanCharge {


    @Override
    public CardState getAttack() {
        return null;
    }

    @Override
    public void setAttack(int attack) {

    }

    @Override
    public void charge(Creature creature) {

    }

    @Override
    public boolean isChargeable() {
        return false;
    }
}
