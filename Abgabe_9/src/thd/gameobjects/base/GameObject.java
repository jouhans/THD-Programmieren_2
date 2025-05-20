package thd.gameobjects.base;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;

import java.util.Objects;

/**
 * Represents an object in the game.
 */
public abstract class GameObject {

    protected final GamePlayManager gamePlayManager;
    protected final GameView gameView;
    protected final Position position;
    protected final Position targetPosition;
    protected double speedInPixel;
    protected double rotation;
    protected double size;
    protected double width;
    protected double height;
    protected char distanceToBackground;
    protected int time;

    /**
     * Crates a new GameObject.
     *
     * @param gameView GameView to show the game object on.
     * @param gamePlayManager link GameObject to the GamePlayManager
     */
    public GameObject(GameView gameView, GamePlayManager gamePlayManager) {
        this.gameView = gameView;
        this.gamePlayManager = gamePlayManager;
        position = new Position();
        targetPosition = new Position();
    }

    /**
     * Return the current distance to the BackgroundGround.
     *
     * @return distanceToBackground
     */
    public char getDistanceToBackground() {
        return distanceToBackground;
    }

    /**
     * Updates the position of the game object.
     */
    public void updatePosition() {
    }

    /**
     * Draws the game object to the canvas.
     */
    public abstract void addToCanvas();

    /**
     * Returns the current position of the game object.
     *
     * @return position of the game object.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Returns width of game object.
     *
     * @return Width of game object
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns height of game object.
     *
     * @return Height of game object
     */
    public double getHeight() {
        return height;
    }

    /**
     * Update the Coordinates of the Game object.
     *
     * @param position position to set the Game object
     */
    public void updateCoordinates(Position position) {}

    /**
     * Update the status of game object.
     */
    public void updateStatus(){}

    @Override
    public boolean equals(Object o){

        if (o == this) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GameObject other = (GameObject) o;
        return position == other.position
                && targetPosition == other.targetPosition
                && Double.compare(speedInPixel, other.speedInPixel) == 0
                && Double.compare(rotation, other.rotation) == 0
                && Double.compare(size, other.size) == 0
                && Double.compare(width, other.width) == 0
                && Double.compare(height, other.height) == 0
                && distanceToBackground == other.distanceToBackground;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, targetPosition, speedInPixel, rotation, size, width, height, distanceToBackground);
    }
}