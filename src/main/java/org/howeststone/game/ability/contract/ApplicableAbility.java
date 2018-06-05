package org.howeststone.game.ability.contract;

public interface ApplicableAbility {
    enum AbilityScope {All, Minions, Hero, Itself, Weapons}

    AbilityScope getScope();

    void applyEffect(AffectableTarget target);

    boolean isValidTarget(AffectableTarget target);
}
