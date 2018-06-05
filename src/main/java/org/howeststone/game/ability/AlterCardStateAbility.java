package org.howeststone.game.ability;

import org.howeststone.game.ability.contract.AffectableTarget;
import org.howeststone.game.ability.contract.ApplicableAbility;
import org.howeststone.game.card.state.CardState;
import org.howeststone.game.contract.Creature;

public class AlterCardStateAbility implements ApplicableAbility {
    private CardState cardStateToAlter;
    private AbilityScope applicableScope;

    public AlterCardStateAbility(CardState cardStateToAlter, AbilityScope scope) {
        this.cardStateToAlter = cardStateToAlter;
        this.applicableScope = scope;
    }

    @Override
    public AbilityScope getScope() {
        return this.applicableScope;
    }

    @Override
    public void applyEffect(AffectableTarget target) {
        if (isValidTarget(target)) {
            target.addState(cardStateToAlter);
        }
    }

    @Override
    public boolean isValidTarget(AffectableTarget target) {
        return (target instanceof Creature);
    }
}
