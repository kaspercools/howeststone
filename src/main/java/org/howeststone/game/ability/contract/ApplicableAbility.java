package org.howeststone.game.ability.contract;

public interface ApplicableAbility {
    AbilityScope getScope();

    void applyEffect(AffectableTarget target);

    boolean isValidTarget(AffectableTarget target);

    enum AbilityScope {All, Minions, Hero, Itself, Weapons}
}
