package org.howeststone.game.ability.contract;

import org.howeststone.game.card.state.CardState;
import org.howeststone.game.card.state.InstanceState;
import org.howeststone.game.contract.Creature;

public interface AffectableTarget {
    void addState(CardState additionalCardState);

    void addState(InstanceState state);
}