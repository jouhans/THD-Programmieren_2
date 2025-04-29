package thd.gameobjects.movable;

import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

class EnemyJetMovementPattern extends MovementPattern {

    private final Position[] endPoints;
    private int currentIndex;
    private double randomY;
    private double randomXStart;
    private double randomXEnd;

    EnemyJetMovementPattern() {
        super();
        randomXStart = random.nextDouble(350);
        randomXEnd = 300.0 + random.nextDouble(900);
        randomY = 100.0 + random.nextDouble(50);
        endPoints = new Position[] {
                new Position(randomXStart, randomY),
                new Position(randomXEnd, randomY)
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
