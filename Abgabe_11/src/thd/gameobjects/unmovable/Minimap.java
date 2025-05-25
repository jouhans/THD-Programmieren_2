package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;

import java.awt.*;

/**
 * Creates a new Minimap Object using {@link Position} for the Position and using {@link GameView} to display it.
 * <p>
 * This class provides methodes to update the Position and to add the Object to the GameView.
 *
 * @see Position
 * @see GameView
 */
public class Minimap extends GameObject {

    /**
     * Initializes a new GameObject "Score".
     *
     * @param gameView link GameObject to the current GameView
     * @param gamePlayManager link GameObject to the GamePlayManager
     */
    public Minimap(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        size = 1.0;
        rotation = 0.0;
        width = 768;
        height = 144;
        distanceToBackground = 1;
        position.updateCoordinates(new Position(GameView.WIDTH/2.0 - width/2, 560));
    }

    @Override
    public void addToCanvas() {
        gameView.addRectangleToCanvas(position.getX(), position.getY(), width, height, 0.0, true, Color.black);
    }

    @Override
    public String toString() {
        return "Minimap: " + position;
    }
}
