package org.howeststone.game;

import org.howeststone.game.card.BaseCard;
import org.howeststone.game.card.Minion;
import org.howeststone.game.card.Spell;
import org.howeststone.game.collection.PlayerTable;
import org.howeststone.game.contract.CanCharge;
import org.howeststone.game.contract.Creature;
import org.howeststone.game.player.Contestant;

import java.util.Collection;
import java.util.List;
import java.util.Random;

public class BattleField {
    private final int cardsPerTurn = 1;
    private final int nCardsForStartingHand = 3;
    private final int nExtraCardsForNonStartingHand = 1;
    private int turnLengthInSeconds = 50;

    private PlayerTable playerTable;
    private Turn currentTurn;

    public BattleField() {
        playerTable = new PlayerTable(2);
    }

    private void initGame() {

        playerTable.getCurrent().drawCards(nCardsForStartingHand);

        playerTable.getAllExceptCurrent().forEach(c ->
                c.drawCards(nCardsForStartingHand + nExtraCardsForNonStartingHand));
    }

    public void addPlayers(List<Contestant> players) {
        players.forEach(c -> playerTable.add(c));
    }

    public void addPlayer(Contestant p) {
        playerTable.add(p);
    }

    public void nextTurn() {
        playerTable.getCurrent().healSummoningSickness();

        currentTurn = new Turn(turnLengthInSeconds);
        playerTable.nextPlayer().drawCards(cardsPerTurn);
    }

    public Contestant coinToss() {
        Random random = new Random(System.currentTimeMillis());
        playerTable.moveCursor(random.nextInt(playerTable.size()));

        initGame();

        return playerTable.getCurrent();
    }

    public boolean isTurnStillActive() {
        return !this.currentTurn.isFinished();
    }

    public void attack(CanCharge source, Creature target) {
        if (isTurnStillActive()) {
            if (target.canBeAttacked() && source.isChargeable()) {
                source.charge(target);
            }
        }
    }

    public void selectCard(int index) {
        playerTable.getCurrent().selectCard(index);
    }

    public void useCard() {
        BaseCard card = playerTable.getCurrent().useCard();
    }

    public void useCard(Creature possibleTarget) {
        if (isTurnStillActive()) {
            BaseCard card = playerTable.getCurrent().useCard();

            if (card instanceof CanCharge) {
                attack((CanCharge) card, possibleTarget);
            }
        }
    }

    public Collection getHand() {
        return this.playerTable.getCurrent().getHand();
    }

    public void setTurnLengthInSeconds(int turnLengthInSeconds) {
        this.turnLengthInSeconds = turnLengthInSeconds;
    }
}
