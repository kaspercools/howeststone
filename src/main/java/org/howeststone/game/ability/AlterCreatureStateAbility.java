package org.howeststone.game.ability;

import org.howeststone.game.ability.contract.AffectableTarget;
import org.howeststone.game.ability.contract.ApplicableAbility;
import org.howeststone.game.card.state.InstanceState;
import org.howeststone.game.contract.Creature;

public class AlterCreatureStateAbility implements ApplicableAbility {
    private InstanceState futureState;
    private AbilityScope applicableScope;

    public AlterCreatureStateAbility(InstanceState stateToAlter, AbilityScope scope) {
        this.futureState = stateToAlter;
        this.applicableScope = scope;
    }

    @Override
    public AbilityScope getScope() {
        return this.applicableScope;
    }

    @Override
    public void applyEffect(AffectableTarget target) {
        if (isValidTarget(target)) {
            target.addState(this.futureState);
        }
    }

    @Override
    public boolean isValidTarget(AffectableTarget target) {
        return (target instanceof Creature);
    }
}


