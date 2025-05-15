package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Position;

/**
 * Creates a new EnemyChopper Shot Object using {@link Position} for the Position and using {@link GameView} to display it.
 * <p>
 * This class provides methodes to update the Position and to add the Object to the GameView.
 *
 * @see Position
 * @see GameView
 */
class EnemyChopperShot extends CollidingGameObject {
    private final EnemyChopperShotMovementPattern enemyChopperShotMovementPattern;
    private final int shotCorrectionYCoordinate;
    private final int shotCorrectionXCoordinate;

    /**
     * Initializes a new GameObject "EnemyChopperShot".
     *
     * @param gameView link GameObject to the current GameView
     * @param gamePlayManager link GameObject to the GamePlayManager
     * @param position get current Position of related GameObject
     */
    protected EnemyChopperShot(GameView gameView, GamePlayManager gamePlayManager, Position position) {
        super(gameView, gamePlayManager);
        speedInPixel = 6;
        size = 0.4;
        width = 28;
        height = 25;
        shotCorrectionXCoordinate = 12;
        shotCorrectionYCoordinate = 12;
        enemyChopperShotMovementPattern = new EnemyChopperShotMovementPattern();
        this.position.updateCoordinates(position.getX() - shotCorrectionXCoordinate, position.getY() + shotCorrectionYCoordinate);
        targetPosition.updateCoordinates(enemyChopperShotMovementPattern.nextPosition(position, shotCorrectionXCoordinate, shotCorrectionYCoordinate));
        hitBoxOffsets(0, 0, 0, 0);
    }

    @Override
    public void updatePosition() {
        if (!targetPosition.similarTo(position)) {
            position.moveToPosition(targetPosition, speedInPixel);
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("enemychoppershot.png", position.getX(), position.getY(), size, rotation);
    }

    @Override
    public String toString() {
        return "Enemy Chopper Shot: " + position;
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof PlayerChopper || other instanceof PlayerChopperShot || other instanceof EnemyJetBomb) {
            gamePlayManager.destroyGameObject(this);
        }
    }
}

