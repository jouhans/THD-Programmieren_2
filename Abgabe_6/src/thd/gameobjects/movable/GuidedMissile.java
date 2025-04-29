package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Position;

/**
 * Creates a new Guided Missile Object using {@link Position} for the Position and using {@link GameView} to display it.
 * <p>
 * This class provides methodes to update the Position and to add the Object to the GameView.
 *
 * @see Position
 * @see GameView
 */
public class GuidedMissile extends CollidingGameObject {
    private final GuidedMissileMovementPattern guidedMissileMovementPattern;

    /**
     * Initializes a new GameObject "EnemyChopper".
     *
     * @param gameView link GameObject to the current GameView
     * @param gamePlayManager link GameObject to the GamePlayManager
     */
    public GuidedMissile(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        speedInPixel = 4;
        size = 0.6;
        width = 150;
        height = 33;
        guidedMissileMovementPattern = new GuidedMissileMovementPattern();
        position.updateCoordinates(guidedMissileMovementPattern.startPosition());
        targetPosition.updateCoordinates(guidedMissileMovementPattern.nextPosition(position));
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {

    }

    @Override
    public void updatePosition() {
        if (!targetPosition.similarTo(position)) {
            position.moveToPosition(targetPosition, speedInPixel);
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("guided_missile.png", position.getX(), position.getY(), size, rotation);
    }

    @Override
    public String toString() {
        return "Guided Missile: " + position;
    }
}

