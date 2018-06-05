package org.howeststone.game.persistence.mocking;

import org.howeststone.game.Deck;
import org.howeststone.game.persistence.DeckDAO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DeckDAOMock implements DeckDAO {
    private static List<Deck> deckList;

    public DeckDAOMock() {
        if (deckList == null) {
            initData();
        }
    }

    private void initData() {
        deckList = new ArrayList<>();

        deckList.add(new Deck());
    }

    @Override
    public Iterable<Deck> findAll() {
        return Collections.unmodifiableCollection(deckList);
    }

    @Override
    public Optional<Deck> findById(int id) {
        return Optional.empty();
    }
}
