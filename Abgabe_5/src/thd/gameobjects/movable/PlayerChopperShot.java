package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;

class PlayerChopperShot extends GameObject {
    private final PlayerChopperShotMovementPattern playerChopperShotMovementPattern;
    private final int shotCorrectionYCoordinate;
    private final int shotCorrectionXCoordinate;

    protected PlayerChopperShot(GameView gameView, GamePlayManager gamePlayManager, Position position) {
        super(gameView, gamePlayManager);
        speedInPixel = 13;
        size = 3.0;
        width = 200;
        height = 33;
        shotCorrectionXCoordinate = 49;
        shotCorrectionYCoordinate = 45;
        playerChopperShotMovementPattern = new PlayerChopperShotMovementPattern();
        this.position.updateCoordinates(position.getX() - shotCorrectionXCoordinate, position.getY() + shotCorrectionYCoordinate);
        targetPosition.updateCoordinates(playerChopperShotMovementPattern.nextPosition(position, shotCorrectionXCoordinate, shotCorrectionYCoordinate));
    }

    @Override
    public void updatePosition() {
        if (!targetPosition.similarTo(position)) {
            position.moveToPosition(targetPosition, speedInPixel);
        }
    }

    @Override
    public void updateStatus() {
        if (position.getX() > width + 1280 || position.getX() < 0 - width) {
            gamePlayManager.destroyGameObject(this);
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(ChopperBlockImages.PLAYER_CHOPPER_SHOT, position.getX(), position.getY(), size, rotation);
    }

    @Override
    public String toString() {
        return "Player Chopper Shot: " + position;
    }
}

