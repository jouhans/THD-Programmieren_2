package thd.gameobjects.movable;

import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

class PlayerChopperShotMovementPattern extends MovementPattern {

    PlayerChopperShotMovementPattern() {
        super();
    }

    Position nextPosition(Position currentPosition, int shotCorrectionXCoordinate, int shotCorrectionYCoordinate) {
        return new Position(-1000, currentPosition.getY() + shotCorrectionYCoordinate);
    }
}
