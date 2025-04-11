package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;

/**
 * Creates a new Truck Object using {@link Position} for the Position and using {@link GameView} to display it.
 * <p>
 * This class provides methodes to update the Position and to add the Object to the GameView.
 *
 * @see Position
 * @see GameView
 */
public class Truck extends GameObject {

    private final TruckMovementPattern truckMovementPattern;
    /**
     * Initializes a new GameObject "Truck".
     *
     * @param gameView link GameObject to the current GameView
     */
    public Truck(GameView gameView) {
        super(gameView);
        speedInPixel = 3;
        size = 30.0;
        rotation = 0.0;
        width = 150;
        height = 33;
        truckMovementPattern = new TruckMovementPattern();
        position.updateCoordinates(truckMovementPattern.startPosition());
        targetPosition.updateCoordinates(truckMovementPattern.nextPosition());
    }

    /**
    * Update the Position of the object.
    * */
    @Override
    public void updatePosition() {
        if (!position.similarTo(targetPosition)) {
            position.moveToPosition(targetPosition, speedInPixel);
        }
    }

    /**
     * Add the object to the canvas.
     */
    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("truck.png", position.getX(), position.getY(), 2, 0);
    }

    @Override
    public String toString() {
        return "Truck: " + position;
    }
}
