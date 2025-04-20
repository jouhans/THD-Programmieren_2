package thd.gameobjects.movable;

import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

class GuidedMissileMovementPattern extends MovementPattern {

    private final Position startPosition;

    GuidedMissileMovementPattern() {
        super();
        startPosition = new Position(400, 400);
    }

    @Override
    protected Position nextPosition(Position currentPosition) {
        return new Position(startPosition);
    }

    @Override
    protected Position startPosition() {
        return startPosition;
    }
}
