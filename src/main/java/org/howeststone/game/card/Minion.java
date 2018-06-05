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
    protected InstanceState minionState;
    protected CardState attackCardState;
    protected CardState healthCardState;
    private List<ApplicableAbility> abilityList;

    private boolean summoningSickness;
    private MinionType type;

    public Minion() {
        summoningSickness = true;
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
        this.minionState = InstanceState.Damaged;
    }

    @Override
    public boolean canBeAttacked() {
        return !minionState.equals(InstanceState.Hidden);
    }

    @Override
    public boolean hasInitialState() {
        return minionState.equals(InstanceState.Init);
    }

    @Override
    public InstanceState getState() {
        return this.minionState;
    }

    @Override
    public void summon() {
        // get applicable abilities for this instance and apply
        this.abilityList.stream().filter(a -> a.getScope().equals(ApplicableAbility.AbilityScope.Itself)).forEach(ability -> ability.applyEffect(this));
    }

    public boolean hasSummoningSickness() {
        return this.minionState.equals(InstanceState.SummoningSickness);
    }

    public void healSummoningSickness() {
        this.minionState = InstanceState.Default;
    }

    public MinionType getType() {
        return type;
    }

    public boolean wasDamaged() {
        return minionState.equals(InstanceState.Damaged);
    }

    public void setType(MinionType type) {
        this.type = type;
    }

    @Override
    public void charge(Creature creature) {
        if (creature.canBeAttacked()) {
            creature.injure(this.attackCardState.getValue());
        }
    }

    @Override
    public boolean isChargeable() {
        return !minionState.equals(InstanceState.SummoningSickness);
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
        this.minionState = state;
    }
}
