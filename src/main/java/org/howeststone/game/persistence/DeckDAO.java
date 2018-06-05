package org.howeststone.game.persistence;

import org.howeststone.game.Deck;

import java.util.Optional;

public interface DeckDAO {
    Iterable<Deck> findAll();
    Optional<Deck> findById(int id);
}
