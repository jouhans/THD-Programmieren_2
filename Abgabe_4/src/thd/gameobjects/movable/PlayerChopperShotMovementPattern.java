package thd.gameobjects.movable;

import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

class PlayerChopperShotMovementPattern extends MovementPattern {

    private final Position startPosition;

    PlayerChopperShotMovementPattern() {
        super();
        startPosition = new Position(500, 200);
    }

    @Override
    protected Position nextPosition(Position currentPosition) {
        return new Position(300, currentPosition.getY());
    }

    @Override
    protected Position startPosition() {
        return startPosition;
    }
}
