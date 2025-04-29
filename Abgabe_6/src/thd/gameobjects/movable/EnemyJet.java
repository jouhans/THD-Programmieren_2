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
public class EnemyJet extends CollidingGameObject {
    private final EnemyJetMovementPattern enemyJetMovementPattern;
    private int shotDurationInMilliseconds;
    private final Random random;


    /**
     * Initializes a new GameObject "EnemyChopper".
     *
     * @param gameView link GameObject to the current GameView
     * @param gamePlayManager link GameObject to the GamePlayManager
     */
    public EnemyJet(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        speedInPixel = 4;
        size = 2.0;
        width = 150;
        height = 33;
        enemyJetMovementPattern = new EnemyJetMovementPattern();
        position.updateCoordinates(enemyJetMovementPattern.startPosition());
        targetPosition.updateCoordinates(enemyJetMovementPattern.nextPosition());
        shotDurationInMilliseconds = gameView.gameTimeInMilliseconds();
        random = new Random();
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {

    }

    @Override
    public void updatePosition() {
        if (targetPosition.similarTo(position)) {
            targetPosition.updateCoordinates(enemyJetMovementPattern.nextPosition());
        }

        position.moveToPosition(targetPosition, speedInPixel);
    }

    @Override
    public void updateStatus() {
        int randomNumber = random.nextInt(3) + 3;
        if (shotDurationInMilliseconds + (randomNumber * 1000) <= gameView.gameTimeInMilliseconds()) {
            gamePlayManager.spawnGameObject(new EnemyJetBomb(gameView, gamePlayManager, position));
            shotDurationInMilliseconds = gameView.gameTimeInMilliseconds();
        }
    }

    private void drop() {
        gamePlayManager.spawnGameObject(new EnemyJetBomb(gameView, gamePlayManager, position));
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("enemyjet.png", position.getX(), position.getY(), size, rotation);
    }

    @Override
    public String toString() {
        return "Enemy Jet: " + position;
    }
}

