package org.howeststone.game;

public class Turn {
    private long durationInMilliseconds;
    private long startEpoch;

    public Turn(int turnLengthInSeconds) {
        this.initTurn(turnLengthInSeconds);
    }

    private void initTurn(int durationInSeconds) {
        this.durationInMilliseconds = durationInSeconds * 1000;
        this.startEpoch = System.currentTimeMillis();
    }

    public boolean isFinished() {
        boolean hasFinished = (System.currentTimeMillis() - this.startEpoch) > durationInMilliseconds;
        return hasFinished;
    }
}
