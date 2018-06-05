package org.howeststone.game.contract;

import org.howeststone.game.card.state.CardState;
import org.howeststone.game.card.state.InstanceState;

import java.util.List;

public interface Creature {

    CardState getHealth();

    void setHealth(int health);

    void injure(int amountOfHealth);

    boolean canBeAttacked();

    boolean hasInitialState();

    List<InstanceState> getStates();
}
