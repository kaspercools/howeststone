package org.howeststone.game.card;

import org.howeststone.game.ability.contract.AffectableTarget;
import org.howeststone.game.ability.contract.ApplicableAbility;
import org.howeststone.game.card.state.CardState;
import org.howeststone.game.card.state.CardStateType;
import org.howeststone.game.card.state.InstanceState;
import org.howeststone.game.contract.CanCharge;
import org.howeststone.game.contract.Creature;
import org.howeststone.game.contract.Summonable;

import java.util.List;

public class Minion extends BaseCard implements Creature, CanCharge, Summonable, AffectableTarget {
    protected int attack;
    protected int health;
    protected List<InstanceState> minionStates;
    protected CardState attackCardState;
    protected CardState healthCardState;
    private List<ApplicableAbility> abilityList;

    private MinionType type;

    public Minion() {
    }

    public Minion(int health, int attack, MinionType type) {
        this();
        this.attack = attack;
        this.health = health;
        this.healthCardState = new CardState(this.health, CardStateType.Health);
        this.attackCardState = new CardState(this.health, CardStateType.Attack);
        this.type = type;
    }

    @Override
    public CardState getAttack() {
        return attackCardState;
    }

    @Override
    public void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public CardState getHealth() {
        return healthCardState;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void injure(int amountOfHealth) {
        this.healthCardState = this.healthCardState.substract(amountOfHealth);
        this.minionStates.add(InstanceState.Damaged);
    }

    @Override
    public boolean canBeAttacked() {
        return !this.minionStates.contains(InstanceState.Hidden);
    }

    @Override
    public boolean hasInitialState() {
        return minionStates.contains(InstanceState.Default);
    }

    @Override
    public List<InstanceState> getStates() {
        return this.minionStates;
    }

    @Override
    public void summon() {
        // get applicable abilities for this instance and apply
        this.abilityList.stream().filter(a -> a.getScope().equals(ApplicableAbility.AbilityScope.Itself)).forEach(ability -> ability.applyEffect(this));
    }

    public boolean hasSummoningSickness() {
        return this.minionStates.contains(InstanceState.SummoningSickness);
    }

    public void healSummoningSickness() {
        this.minionStates.remove(InstanceState.Default);
    }

    public MinionType getType() {
        return type;
    }

    public void setType(MinionType type) {
        this.type = type;
    }

    public boolean wasDamaged() {
        return minionStates.contains(InstanceState.Damaged);
    }

    @Override
    public void charge(Creature creature) {
        if (creature.canBeAttacked()) {
            creature.injure(this.attackCardState.getValue());
        }
    }

    @Override
    public boolean isChargeable() {
        return !minionStates.contains(InstanceState.SummoningSickness);
    }

    @Override
    public void addState(CardState additionalCardState) {
        // TODO: collect states history
        if (additionalCardState.getType().equals(CardStateType.Health)) {
            this.healthCardState.add(additionalCardState);
        } else if (additionalCardState.getType().equals(CardStateType.Attack)) {
            this.attackCardState.add(additionalCardState);
        }
    }

    @Override
    public void addState(InstanceState state) {
        // TODO: better option would be to implement finite state machine, but that would be a bit far fetched
        this.minionStates.add(state);
    }
}
