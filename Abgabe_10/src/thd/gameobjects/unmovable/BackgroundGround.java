package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Position;

/**
 * Creates a new BackgroundGround Object using {@link Position} for the Position and using {@link GameView} to display it.
 * <p>
 * This class provides methodes to update the Position and to add the Object to the GameView.
 *
 * @see Position
 * @see GameView
 */
public class BackgroundGround extends CollidingGameObject {
    /**
     * Initializes a new GameObject "BackgroundGround".
     *
     * @param gameView link GameObject to the current GameView
     * @param gamePlayManager link GameObject to the GamePlayManager
     */
    public BackgroundGround(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        size = 1.0;
        width = 1280;
        height = 175;
        distanceToBackground = 0;
        position.updateCoordinates(new Position(0, 545));
        hitBoxOffsets(0, 0, 0, 0);
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
    }

    /**
    * Update the Position of the object.
    * */
    @Override
    public void updatePosition() {
    }

    /**
     * Add the object to the canvas.
     */
    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("background_ground.png", position.getX(), position.getY(), size, rotation);
    }

    @Override
    public String toString() {
        return "BackgroundGround: " + position;
    }
}
