package org.howeststone.game;

import org.howeststone.game.card.Minion;
import org.howeststone.game.card.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HeroTest {
    private Minion opponent;
    private Hero hero;

    @BeforeEach
    void beforeEachTest() {
        opponent = new Minion();
        hero = new Hero();
    }

    @Test
    void testInjure() {
        int heroInitialHealth = hero.getHealth().getValue();
        hero.injure(5);

        assertEquals(hero.getHealth().getValue(), heroInitialHealth - 5);
    }


}
