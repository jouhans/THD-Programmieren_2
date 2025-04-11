package thd.gameobjects.movable;

import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

class EnemyChopperMovementPattern extends MovementPattern {

    private final Position[] endPoints;
    private int currentIndex;

    EnemyChopperMovementPattern() {
        super();
        endPoints = new Position[] {
                new Position(0, 200),
                new Position(200, 200),
                new Position(200, 300),
                new Position(800, 300),
                new Position(800, 150),
                new Position(800, 200),
                new Position(1720, 200),

        };
    }

    @Override
    protected Position nextPosition() {
        if (currentIndex >= endPoints.length) {
            currentIndex = 0;
        }
        return endPoints[currentIndex++];
    }

    @Override
    protected Position startPosition() {
        currentIndex = 0;
        return nextPosition();
    }
}
