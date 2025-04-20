package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;

/**
 * Creates a new EnemyChopper Shot Object using {@link Position} for the Position and using {@link GameView} to display it.
 * <p>
 * This class provides methodes to update the Position and to add the Object to the GameView.
 *
 * @see Position
 * @see GameView
 */
public class EnemyChopperShot extends GameObject {
    private final EnemyChopperShotMovementPattern enemyChopperShotMovementPattern;

    /**
     * Initializes a new GameObject "EnemyChopperShot".
     *
     * @param gameView link GameObject to the current GameView
     * @param gamePlayManager link GameObject to the GamePlayManager
     */
    public EnemyChopperShot(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        speedInPixel = 4;
        size = 0.4;
        width = 200;
        height = 33;
        enemyChopperShotMovementPattern = new EnemyChopperShotMovementPattern();
        position.updateCoordinates(enemyChopperShotMovementPattern.startPosition());
        targetPosition.updateCoordinates(enemyChopperShotMovementPattern.nextPosition(position));
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
}

