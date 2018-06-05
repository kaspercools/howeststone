package org.howeststone.game;

import org.howeststone.game.card.BaseCard;

import java.util.*;

public class Deck {
    private int id;
    private int maxCapacity;
    private LinkedList<BaseCard> cardList;
    private int emptyDeckPenalty;

    public Deck() {
        cardList = new LinkedList<>();
        this.maxCapacity = 30;
        this.emptyDeckPenalty = 0;
    }

    public boolean isFull() {
        return cardList.size() == maxCapacity;
    }

    public int getId() {
        return id;
    }

    public boolean canAddCard(BaseCard card) {
        boolean canAddCard;
        canAddCard = !this.isFull();

        if (card.isLegendary()) {
            canAddCard &= !this.cardList.contains(card);
        } else {
            canAddCard &= this.cardList.stream().filter(c -> c.equals(card)).toArray().length < 2;
        }

        return canAddCard;
    }

    public void addCard(BaseCard card) {
        if (this.canAddCard(card)) {
            this.cardList.add(card);
        }
    }

    public void addCards(Collection<BaseCard> cards) {
        if (!this.isFull()) {
            cards.stream().limit((maxCapacity - cardList.size())).forEach(c -> {
                if (this.canAddCard(c)) {
                    this.cardList.add(c);
                }
            });
        }

    }

    public Collection<BaseCard> getCards() {
        return Collections.unmodifiableCollection(cardList);
    }

    public void removeCard(BaseCard card) {
        this.cardList.remove(card);
    }

    public Collection<BaseCard> drawCards(int amount) {
        List<BaseCard> cards = new ArrayList<>();
        while (amount > 0 && cards.size() > 0) {
            cards.add(cardList.poll());
        }

        if (cardList.isEmpty() && cards.size() < amount) {
            emptyDeckPenalty += 1;
        }

        return Collections.unmodifiableCollection(cards);
    }

    public int getEmptyDeckPenalty() {
        return emptyDeckPenalty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Deck)) return false;
        Deck deck = (Deck) o;
        return getId() == deck.getId();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
    }
}
