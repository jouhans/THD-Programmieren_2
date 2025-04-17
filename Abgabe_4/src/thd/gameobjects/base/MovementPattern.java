package thd.gameobjects.base;

import java.util.Random;

/**
 * Represents a movement pattern for an object in the game.
 */
public class MovementPattern {
    protected final Random random;

    protected MovementPattern(){
        random = new Random();
    }

    protected Position nextPosition() {
        return new Position(0, 0);
    }

    protected Position nextPosition(Position currentPosition) {
        return new Position(0, 0);
    }

    protected Position startPosition() {
        return new Position(0, 0);
    }
}
