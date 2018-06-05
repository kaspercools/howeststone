package org.howeststone.game;

import org.howeststone.game.ability.contract.AffectableTarget;
import org.howeststone.game.ability.contract.ApplicableAbility;
import org.howeststone.game.card.Weapon;
import org.howeststone.game.card.state.CardState;
import org.howeststone.game.card.state.CardStateType;
import org.howeststone.game.card.state.InstanceState;
import org.howeststone.game.contract.CanCharge;
import org.howeststone.game.contract.Creature;
import sun.jvm.hotspot.oops.Instance;

public class Hero implements Creature, CanCharge, AffectableTarget {
    private int health;
    private String name;
    private Weapon weapon;
    private ApplicableAbility ability;
    protected InstanceState heroState;
    protected CardState attackCardState;
    protected CardState healthCardState;

    public Hero() {
        this.health = 30;
        this.healthCardState = new CardState(this.health, CardStateType.Health);
    }

    public Hero(ApplicableAbility ability) {
        this.ability = ability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ApplicableAbility getAbility() {
        return ability;
    }

    public void setAbility(ApplicableAbility ability) {
        this.ability = ability;
    }

    @Override
    public CardState getHealth() {
        return this.healthCardState;
    }

    @Override
    public void setHealth(int health) {
        this.healthCardState = this.healthCardState.add(health);
    }

    @Override
    public CardState getAttack() {
        return this.attackCardState;
    }

    @Override
    public void setAttack(int attack) {

    }

    @Override
    public void injure(int amountOfHealth) {
        this.healthCardState = this.healthCardState.substract(amountOfHealth);
    }

    @Override
    public boolean canBeAttacked() {
        return false;
    }

    @Override
    public boolean hasInitialState() {
        return false;
    }

    @Override
    public InstanceState getState() {
        return this.heroState;
    }

    void drawWeapon(Creature opponent) {
        weapon.charge(opponent);

        if (opponent instanceof CanCharge) {
            ((CanCharge) opponent).charge(this);
        }
    }

    @Override
    public void charge(Creature creature) {
        this.drawWeapon(creature);
    }

    @Override
    public boolean isChargeable() {
        return true;
    }

    public void setWeapon(Weapon newWeapon) {
        this.weapon = newWeapon;
    }

    @Override
    public void addState(CardState additionalCardState) {
        if (additionalCardState.getType().equals(CardStateType.Health)) {
            this.healthCardState.add(additionalCardState);
        } else if (additionalCardState.getType().equals(CardStateType.Attack)) {
            this.attackCardState.add(additionalCardState);
        }
    }

    @Override
    public void addState(InstanceState state) {
        this.heroState = state;
    }
}
