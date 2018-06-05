package org.howeststone.game.player;

import org.howeststone.game.Deck;
import org.howeststone.game.Hero;
import org.howeststone.game.card.BaseCard;
import org.howeststone.game.card.Minion;
import org.howeststone.game.contract.Summonable;

import java.util.*;

public class Contestant {
    private Hero hero;
    private int availableMana;
    private LinkedList<BaseCard> hand;

    private LinkedList<Summonable> battleField;
    private Deck deck;

    private String name;
    private BaseCard selectedCard;

    public Contestant() {
        hand = new LinkedList<>();
        battleField = new LinkedList<>();
        deck = new Deck();
    }

    public Contestant(String name) {
        this();
        this.name = name;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contestant)) return false;
        Contestant contestant = (Contestant) o;
        return Objects.equals(name, contestant.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }

    public BaseCard useCard() {
        if (this.reduceMana(selectedCard.getManaCost())) {
            hand.remove(selectedCard);

            if (selectedCard instanceof Summonable) {
                Summonable summonAbleCard = (Summonable) selectedCard;
                this.battleField.add(summonAbleCard);
                summonAbleCard.summon();
            }

            return selectedCard;
        }
        return null;
    }

    private boolean reduceMana(int manaCost) {
        if (manaCost > this.availableMana) {
            return false;
        } else {
            this.availableMana = -manaCost;
            return true;
        }
    }

    public void drawCards(int amount) {
        Collection<BaseCard> newCards = this.deck.drawCards(3);
        this.hand.addAll(newCards);
        if (newCards.size() < amount) { // doesn't really matter but we'll check it anyway
            this.getHero().injure(this.deck.getEmptyDeckPenalty());
        }
    }

    public void addToBattleField(Minion minion) {
        this.battleField.add(minion);
    }

    public void addToBattleField(Minion minion, int index) {
        this.battleField.add(index, minion);
    }

    public void healSummoningSickness() {
        this.battleField.stream().filter(m -> m.hasSummoningSickness()).forEach(m -> m.healSummoningSickness());
    }

    public Collection getHand() {
        return Collections.unmodifiableCollection(this.hand);
    }

    public void selectCard(int index) {
        this.selectedCard = hand.get(index);
    }

}

