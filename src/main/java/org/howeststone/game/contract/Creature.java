package org.howeststone.game.contract;

import org.howeststone.game.card.state.CardState;
import org.howeststone.game.card.state.InstanceState;

public interface Creature {

    CardState getHealth();

    void setHealth(int health);

    void injure(int amountOfHealth);

    boolean canBeAttacked();

    boolean hasInitialState();

    InstanceState getState();
}
