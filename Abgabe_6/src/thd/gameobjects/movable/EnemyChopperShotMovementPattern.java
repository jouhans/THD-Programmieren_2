package thd.gameobjects.movable;

import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

class EnemyChopperShotMovementPattern extends MovementPattern {

    EnemyChopperShotMovementPattern() {
        super();
    }

    Position nextPosition(Position currentPosition, int shotCorrectionXCoordinate, int shotCorrectionYCoordinate) {
        return new Position(500, currentPosition.getY() + shotCorrectionYCoordinate);
    }
}
