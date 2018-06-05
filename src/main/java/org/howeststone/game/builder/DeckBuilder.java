package org.howeststone.game.builder;

import org.howeststone.game.Deck;
import org.howeststone.game.card.BaseCard;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DeckBuilder {
    private List<BaseCard> cardList;
    private Deck selectedDeck;

    public DeckBuilder(List<BaseCard> cardList) {
        this.cardList = cardList;
    }

    public void addCard(BaseCard card) {
        selectedDeck.addCard(card);
    }

    public void removeCard(BaseCard card) {
        selectedDeck.removeCard(card);
    }

    public void SaveDeck() {
        if (selectedDeck.isFull()) {
            //save
        }
    }

    public Collection<BaseCard> getAvailableCards() {
        return Collections.unmodifiableCollection(cardList);
    }

    public Collection<BaseCard> getCardsInDeck() {
        return selectedDeck.getCards();
    }
}
