package org.howeststone.game.contract;

import org.howeststone.game.card.state.CardState;

public interface CanCharge {
    CardState getAttack();

    void setAttack(int attack);

    void charge(Creature creature);

    boolean isChargeable();
}
