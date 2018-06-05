package org.howeststone.game.card;

import org.howeststone.game.ability.contract.AffectableTarget;
import org.howeststone.game.card.state.InstanceState;
import org.howeststone.game.contract.CanCharge;
import org.howeststone.game.contract.Creature;
import org.howeststone.game.card.state.CardState;
import org.howeststone.game.card.state.CardStateType;

public class Weapon extends BaseCard implements CanCharge, AffectableTarget {
    private int durability;
    private int attack;
    private CardState attackCardState;
    private CardState durabilityCardState;

    private boolean unsheathed;

    public Weapon(String name, String description, int durability, int mana) {
        super(name, description, mana);

        this.durability = durability;
    }

    public boolean isUnsheathed() {
        return unsheathed;
    }

    public void setUnsheathed(boolean unsheathed) {
        this.unsheathed = unsheathed;
    }

    @Override
    public CardState getAttack() {
        return this.attackCardState;
    }

    @Override
    public void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public void charge(Creature creature) {
        creature.injure(this.getAttack().getValue());
        this.durabilityCardState = durabilityCardState.substract(1);
    }

    @Override
    public boolean isChargeable() {
        return true;
    }

    @Override
    public void addState(CardState additionalCardState) {
        if (additionalCardState.getType().equals(CardStateType.Attack)) {
            this.attackCardState = attackCardState.add(additionalCardState);
        } else if (additionalCardState.getType().equals(CardStateType.Durability)) {
            this.durabilityCardState = durabilityCardState.add(additionalCardState);
        }
    }

    @Override
    public void addState(InstanceState state) {
        this.instanceState = state;
    }
}
