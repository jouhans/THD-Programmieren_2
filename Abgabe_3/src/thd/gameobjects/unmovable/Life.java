package thd.gameobjects.unmovable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;

/**
 * Creates a new Life Object using {@link Position} for the Position and using {@link GameView} to display it.
 * <p>
 * This class provides methodes to update the Position and to add the Object to the GameView.
 *
 * @see Position
 * @see GameView
 */
public class Life extends GameObject {

    /**
     * Initializes a new GameObject "Life".
     *
     * @param gameView link GameObject to the current GameView
     */
    public Life(GameView gameView) {
        super(gameView);
        size = 30.0;
        rotation = 0.0;
        width = 150;
        height = 33;
        position.updateCoordinates(new Position(GameView.WIDTH - width, 0));
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("life.png", position.getX(), position.getY(), 2.0, rotation);
    }

    @Override
    public String toString() {
        return "Score: " + position;
    }
}
