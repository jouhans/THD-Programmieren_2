package thd.gameobjects.unmovable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.Position;

/**
 * Creates a new Life Object using {@link Position} for the Position and using {@link GameView} to display it.
 * <p>
 * This class provides methodes to update the Position and to add the Object to the GameView.
 *
 * @see Position
 * @see GameView
 */
public class Life {

    private final GameView gameView;
    private final Position position;

    private final double size;
    private final double rotation;

    private final double width;
    private final double height;

    /**
     * Initializes a new GameObject "Life".
     *
     * @param gameView link GameObject to the current GameView
     */
    public Life(GameView gameView) {
        this.gameView = gameView;
        this.size = 30.0;
        this.rotation = 0.0;
        this.width = 150;
        this.height = 33;
        this.position = new Position(GameView.WIDTH - width, 0);
    }

    /**
     * Add the object to the canvas.
     */
    public void addToCanvas() {
        gameView.addImageToCanvas("life.png", position.getX(), position.getY(), 2.0, rotation);
    }

    @Override
    public String toString() {
        return "Score: " + position;
    }
}
