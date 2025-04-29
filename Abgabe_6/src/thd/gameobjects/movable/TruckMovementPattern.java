package thd.gameobjects.movable;

import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

class TruckMovementPattern extends MovementPattern {

    private final Position startPosition;
    private final Position endPosition;

    TruckMovementPattern() {
        super();
        startPosition = new Position(1720, 500);
        endPosition = new Position(0, 500);

    }

    @Override
    protected Position nextPosition() {
        return endPosition;
    }

    @Override
    protected Position startPosition() {
        return startPosition;
    }
}
