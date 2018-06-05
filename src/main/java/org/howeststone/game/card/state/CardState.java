package org.howeststone.game.card.state;

public class CardState {
    private int value;
    private CardStateType type;

    public CardState(int value, CardStateType type) {
        this.value = value;
        this.type = type;
    }

    public CardState(CardStateType type) {
        this.type = type;
    }

    public CardStateType getType() {
        return type;
    }

    public CardState(int health) {
        this.value = health;
    }

    public int getValue() {
        return value;
    }

    public CardState adjust(int amount) {
        return new CardState(amount);
    }

    public CardState substract(int amount) {
        return new CardState(this.getValue() - amount);
    }

    public CardState add(int amount) {
        return new CardState(this.getValue() + amount);
    }

    public CardState substract(CardState cardState) {
        return new CardState(this.getValue() - ((CardState) cardState).getValue());
    }

    public CardState add(CardState cardState) {
        return new CardState(this.getValue() + ((CardState) cardState).getValue());
    }
}
