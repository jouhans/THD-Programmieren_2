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
public class EnemyJet extends GameObject {
    private final EnemyJetMovementPattern enemyJetMovementPattern;

    /**
     * Initializes a new GameObject "EnemyChopper".
     *
     * @param gameView link GameObject to the current GameView
     */
    public EnemyJet(GameView gameView) {
        super(gameView);
        speedInPixel = 4;
        size = 2.0;
        width = 150;
        height = 33;
        enemyJetMovementPattern = new EnemyJetMovementPattern();
        position.updateCoordinates(enemyJetMovementPattern.startPosition());
        targetPosition.updateCoordinates(enemyJetMovementPattern.nextPosition());
    }

    @Override
    public void updatePosition() {
        if (targetPosition.similarTo(position)) {
            targetPosition.updateCoordinates(enemyJetMovementPattern.nextPosition());
        }

        position.moveToPosition(targetPosition, speedInPixel);
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

