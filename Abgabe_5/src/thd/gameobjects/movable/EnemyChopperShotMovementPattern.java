package thd.gameobjects.movable;

import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

class EnemyChopperShotMovementPattern extends MovementPattern {

    private final Position startPosition;

    EnemyChopperShotMovementPattern() {
        super();
        startPosition = new Position(700, 400);
    }

    @Override
    protected Position nextPosition(Position currentPosition) {
        return new Position(500, currentPosition.getY());
    }

    @Override
    protected Position startPosition() {
        return startPosition;
    }
}
