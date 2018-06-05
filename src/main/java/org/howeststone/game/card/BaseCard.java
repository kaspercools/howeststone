package org.howeststone.game.card;

import org.howeststone.game.ability.contract.ApplicableAbility;
import org.howeststone.game.card.state.InstanceState;

import java.util.Objects;
import java.util.Set;

public abstract class BaseCard {
    private int id;

    protected int mana;
    protected String name;
    protected String description;
    protected CardRarity rarity;
    protected boolean destroyed;
    private Set<ApplicableAbility> abilitySet;
    protected InstanceState instanceState;

    public BaseCard() {
        this.destroyed = false;
    }


    public BaseCard(String name, String description, int mana) {
        this();

        this.mana = mana;
        this.name = name;
        this.description = description;

    }

    public int getManaCost() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public void burn() {
        this.destroyed = true;
    }

    @Override
    public String toString() {
        return "BaseCard{" +
                ", mana=" + mana +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseCard)) return false;
        BaseCard baseCard = (BaseCard) o;
        return id == baseCard.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    public boolean isLegendary() {
        return this.rarity == CardRarity.Legendary;
    }
}
