package thd.gameobjects.movable;

import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

class EnemyJetBombMovementPattern extends MovementPattern {

    private final Position startPosition;

    EnemyJetBombMovementPattern() {
        super();
        startPosition = new Position(500, 200);
    }

    @Override
    protected Position nextPosition(Position currentPosition) {
        return new Position(currentPosition.getX(), 500);
    }

    @Override
    protected Position startPosition() {
        return startPosition;
    }
}
