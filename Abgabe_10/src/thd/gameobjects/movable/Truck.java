package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.base.ShiftableGameObject;

/**
 * Creates a new Truck Object using {@link Position} for the Position and using {@link GameView} to display it.
 * <p>
 * This class provides methodes to update the Position and to add the Object to the GameView.
 *
 * @see Position
 * @see GameView
 */
public class Truck extends CollidingGameObject implements ShiftableGameObject {

    /**
     * Initializes a new GameObject "Truck".
     *
     * @param gameView link GameObject to the current GameView
     * @param gamePlayManager link GameObject to the GamePlayManager
     */
    public Truck(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        speedInPixel = 2;
        size = 2.0;
        width = 62;
        height = 43;
        distanceToBackground = 4;
        position.updateCoordinates(new Position(1720, 500));
        hitBoxOffsets(0, 0, 0, 0);
    }

    /**
     * Initializes a new GameObject "Truck".
     *
     * @param gameView link GameObject to the current GameView
     * @param gamePlayManager link GameObject to the GamePlayManager
     * @param xCoordinate link xCoordinate to spawn the truck at this position
     */
    public Truck(GameView gameView, GamePlayManager gamePlayManager, int xCoordinate) {
        super(gameView, gamePlayManager);
        speedInPixel = 2;
        size = 2.0;
        width = 62;
        height = 43;
        distanceToBackground = 4;
        this.position.updateCoordinates(new Position(xCoordinate, 500));
        hitBoxOffsets(0, 0, 0, 0);
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof EnemyJetBomb) {
            gamePlayManager.destroyGameObject(this);
        }
    }

    /**
    * Update the Position of the object.
    * */
    @Override
    public void updatePosition() {
        position.left(speedInPixel);
    }

    /**
     * Add the object to the canvas.
     */
    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("truck.png", position.getX(), position.getY(), size, rotation);
    }

    @Override
    public String toString() {
        return "Truck: " + position;
    }
}
