package org.howeststone.game.ability.contract;

import org.howeststone.game.card.state.CardState;
import org.howeststone.game.card.state.InstanceState;

public interface AffectableTarget {
    void addState(CardState additionalCardState);

    void addState(InstanceState state);
}