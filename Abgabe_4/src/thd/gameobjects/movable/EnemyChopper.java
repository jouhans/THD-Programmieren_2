package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;

/**
 * Creates a new enemy helicopter Object using {@link Position} for the Position and using {@link GameView} to display it.
 * <p>
 * This class provides methodes to update the Position and to add the Object to the GameView.
 *
 * @see Position
 * @see GameView
 */
public class EnemyChopper extends GameObject {
    private final EnemyChopperMovementPattern enemyChopperMovementPattern;

    /**
     * Initializes a new GameObject "EnemyChopper".
     *
     * @param gameView link GameObject to the current GameView
     */
    public EnemyChopper(GameView gameView) {
        super(gameView);
        speedInPixel = 4;
        size = 1.0;
        width = 150;
        height = 33;
        enemyChopperMovementPattern = new EnemyChopperMovementPattern();
        position.updateCoordinates(enemyChopperMovementPattern.startPosition());
        targetPosition.updateCoordinates(enemyChopperMovementPattern.nextPosition());
    }

    @Override
    public void updatePosition() {
        if (targetPosition.similarTo(position)) {
            targetPosition.updateCoordinates(enemyChopperMovementPattern.nextPosition());
        }

        position.moveToPosition(targetPosition, speedInPixel);
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("enemychopper.png", position.getX(), position.getY(), size, rotation);
    }

    @Override
    public String toString() {
        return "Enemy Chopper: " + position;
    }
}

