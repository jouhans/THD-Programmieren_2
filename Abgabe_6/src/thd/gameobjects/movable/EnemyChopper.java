package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Position;

import java.util.Random;

/**
 * Creates a new enemy helicopter Object using {@link Position} for the Position and using {@link GameView} to display it.
 * <p>
 * This class provides methodes to update the Position and to add the Object to the GameView.
 *
 * @see Position
 * @see GameView
 */
public class EnemyChopper extends CollidingGameObject {
    private final EnemyChopperMovementPattern enemyChopperMovementPattern;
    private int shotDurationInMilliseconds;
    private final Random random;

    /**
     * Initializes a new GameObject "EnemyChopper".
     *
     * @param gameView link GameObject to the current GameView
     * @param gamePlayManager link GameObject to the GamePlayManager
     */
    public EnemyChopper(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        speedInPixel = 3;
        size = 1.0;
        width = 150;
        height = 33;
        enemyChopperMovementPattern = new EnemyChopperMovementPattern();
        position.updateCoordinates(enemyChopperMovementPattern.startPosition());
        targetPosition.updateCoordinates(enemyChopperMovementPattern.nextPosition());
        shotDurationInMilliseconds = gameView.gameTimeInMilliseconds();
        random = new Random();
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {

    }

    @Override
    public void updatePosition() {
        if (targetPosition.similarTo(position)) {
            targetPosition.updateCoordinates(enemyChopperMovementPattern.nextPosition());
        }
        position.moveToPosition(targetPosition, speedInPixel);
    }

    @Override
    public void updateStatus() {
        shootAShot();
    }

    private void shootAShot() {
        int randomNumber = random.nextInt(3) + 1;
        if (shotDurationInMilliseconds + (randomNumber * 1000) <= gameView.gameTimeInMilliseconds()) {
            gamePlayManager.spawnGameObject(new EnemyChopperShot(gameView, gamePlayManager, position));
            shotDurationInMilliseconds = gameView.gameTimeInMilliseconds();
        }
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

