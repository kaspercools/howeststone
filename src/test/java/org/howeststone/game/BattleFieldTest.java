package org.howeststone.game;


import org.howeststone.game.player.Contestant;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.*;


public class BattleFieldTest {
    private BattleField battleField;

    @BeforeEach
    public void setUp() throws Exception {
        battleField = new BattleField();
        Contestant player = new Contestant("Player 1");
        player.setHero(new Hero());
        Contestant player2 = new Contestant("Player 2");
        player2.setHero(new Hero());

        battleField.addPlayers(Arrays.asList(player, player2));
    }

    @AfterEach
    public void tearDown() throws Exception {

    }

    @Test
    public void turnNotValidAfterNSeconds() {
        battleField.setTurnLengthInSeconds(2);
        battleField.nextTurn();

        long start = System.currentTimeMillis();

        await().atMost(3, SECONDS).until(() -> {
            return System.currentTimeMillis() - start > 2 * 1000;
        });

        assertFalse(battleField.isTurnStillActive());
    }

    @Test
    public void coinToss() {
        Contestant chosenPlayer = battleField.coinToss();

        assertNotNull(chosenPlayer);
    }

    @Test
    public void drawCardDamage() {
        Contestant chosenPlayer = battleField.coinToss();
        battleField.nextTurn();
        assertEquals(29, chosenPlayer.getHero().getHealth().getValue());
    }
}
