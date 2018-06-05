package org.howeststone.game.collection;

import org.howeststone.game.player.Contestant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class PlayerTableTest {
    private PlayerTable playerList;

    @BeforeEach
    public void initTest() {
        playerList = new PlayerTable(2);
        playerList.add(new Contestant("Player1"));
        playerList.add(new Contestant("Player2"));
    }

    @Test
    public void nextPlayer() {
        Contestant currentContestant = playerList.moveCursor(0);

        Contestant nextContestant = playerList.nextPlayer();

        assertEquals(nextContestant.getName(), "Player2");
    }

    @Test
    public void addBeyondCapacity() {
        assertFalse(playerList.add(new Contestant("Player3")));
    }

    @Test
    public void addWithinCapacity() {
        playerList.remove(playerList.getCurrent());

        assertTrue(playerList.add(new Contestant("Player3")));
    }
}
