package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.Position;

/**
 * Creates a new enemy helicopter Object using {@link Position} for the Position and using {@link GameView} to display it.
 * <p>
 * This class provides methodes to update the Position and to add the Object to the GameView.
 *
 * @see Position
 * @see GameView
 */
public class EnemyChopper {

    private final GameView gameView;
    private final Position position;

    private final double speedInPixel;
    private final double size;
    private final double rotation;

    private final double width;
    private final double height;

    /**
     * Initializes a new GameObject "EnemyChopper".
     *
     * @param gameView link GameObject to the current GameView
     */
    public EnemyChopper(GameView gameView) {
        this.gameView = gameView;
        this.position = new Position(1100, 450);
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
        position.left(speedInPixel);
    }

    /**
     * Add the object to the canvas.
     */
    public void addToCanvas() {
        gameView.addImageToCanvas("enemychopper.png", position.getX(), position.getY(), 1, 0.0);
    }

    @Override
    public String toString() {
        return "Enemy Chopper: " + position;
    }
}

