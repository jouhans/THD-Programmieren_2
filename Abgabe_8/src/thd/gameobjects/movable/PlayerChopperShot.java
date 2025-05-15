package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.base.ShiftableGameObject;

class PlayerChopperShot extends CollidingGameObject implements ShiftableGameObject {
    private final int shotCorrectionYCoordinate;
    private final int shotCorrectionXCoordinate;

    protected PlayerChopperShot(GameView gameView, GamePlayManager gamePlayManager, Position position) {
        super(gameView, gamePlayManager);
        speedInPixel = 13;
        size = 3.0;
        width = 80;
        height = 4;
        shotCorrectionXCoordinate = 49;
        shotCorrectionYCoordinate = 45;
        distanceToBackground = 4;
        this.position.updateCoordinates(position.getX() - shotCorrectionXCoordinate, position.getY() + shotCorrectionYCoordinate);
        hitBoxOffsets(0, 0, 0, 0);
        time = gameView.gameTimeInMilliseconds();
    }

    @Override
    public void updatePosition() {
        if (gameView.gameTimeInMilliseconds() < time + 4000) {
            position.left(speedInPixel);
        } else {
            gamePlayManager.destroyGameObject(this);
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

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof EnemyJetBomb || other instanceof EnemyChopperShot || other instanceof EnemyJet || other instanceof EnemyChopper) {
            gamePlayManager.destroyGameObject(this);
        }
    }
}

