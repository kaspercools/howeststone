package org.howeststone.game.collection;


import org.howeststone.game.player.Contestant;

import java.util.*;
import java.util.stream.Collectors;


public class PlayerTable {
    private final LinkedList<PlayerNode> players; // a List implementing RandomAccess
    private PlayerNode current;
    private final int maxCapacity;

    public PlayerTable(int capacity) {
        maxCapacity = capacity;
        players = new LinkedList<>();
    }

    public Contestant moveCursor(int index) {
        current = players.get(index);
        return current.getContestant();
    }

    public Contestant nextPlayer() {
        current = current.getNext();

        return current.getContestant();
    }

    public boolean add(Contestant contestant) {
        PlayerNode newPlayerNode;
        PlayerNode lastNode;

        if (this.size() >= maxCapacity) {
            return false;
        }

        if (players.size() > 0) {
            newPlayerNode = new PlayerNode(contestant, players.getFirst());
            lastNode = players.getLast();
            lastNode.setNext(newPlayerNode);
        } else {
            // if empty list set curent node
            newPlayerNode = new PlayerNode(contestant);
            current = newPlayerNode;
        }

        return players.add(newPlayerNode);
    }

    public int size() {
        return players.size();
    }

    public Contestant getCurrent() {
        return current.getContestant();
    }

    public void remove(Contestant contestant) {
        players.remove(this.findPlayerNode(contestant));
    }

    public Collection<Contestant> getAll() {
        List result = players.stream().map(c -> c.contestant).collect(Collectors.toList());

        return result;
    }

    public Collection<Contestant> getAllExceptCurrent() {
        List result = players.stream().filter(c -> !c.equals(current)).map(c -> c.contestant).collect(Collectors.toList());

        return result;
    }

    private PlayerNode findPlayerNode(Contestant contestant) {
        return players.stream().filter(n -> n.getContestant().equals(contestant)).findFirst().get();
    }

    private class PlayerNode {
        private Contestant contestant;
        private PlayerNode next;

        public PlayerNode(Contestant contestant) {
            this.contestant = contestant;
        }

        public PlayerNode(Contestant contestant, PlayerNode next) {
            this.contestant = contestant;
            this.next = next;
        }

        public Contestant getContestant() {
            return contestant;
        }

        public PlayerNode getNext() {
            return next;
        }

        public void setNext(PlayerNode next) {
            this.next = next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PlayerNode)) return false;
            PlayerNode that = (PlayerNode) o;
            return Objects.equals(getContestant(), that.getContestant());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getContestant());
        }
    }
}