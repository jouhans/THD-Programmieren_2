package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.Position;

/**
 * Creates a new Truck Object using {@link Position} for the Position and using {@link GameView} to display it.
 * <p>
 * This class provides methodes to update the Position and to add the Object to the GameView.
 *
 * @see Position
 * @see GameView
 */
public class Truck {

    private final double speedInPixel;
    private final double size;
    private double rotation;

    private final GameView gameView;
    private final Position position;

    private final double width;
    private final double height;

    /**
     * Initializes a new GameObject "Truck".
     *
     * @param gameView link GameObject to the current GameView
     */
    public Truck(GameView gameView) {
        this.gameView = gameView;
        this.position = new Position(150, GameView.HEIGHT / 2.0);
        this.speedInPixel = 0;
        this.size = 30.0;
        this.rotation = 0.0;
        this.width = 150;
        this.height = 33;
    }
    /**
    * Update the Position of the object.
    * */
    public void updatePosition() {
        position.right(speedInPixel);
    }

    /**
     * Add the object to the canvas.
     */
    public void addToCanvas() {
        gameView.addImageToCanvas("truck.png", position.getX(), position.getY(), 2, 0);
    }

    @Override
    public String toString() {
        return "Truck: " + position;
    }
}
