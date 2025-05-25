package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
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
     * @param gamePlayManager link GameObject to the GamePlayManager
     */
    public Life(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        size = 2.0;
        width = 150;
        height = 33;
        distanceToBackground = 3;
        position.updateCoordinates(new Position(GameView.WIDTH - width, 0));
    }

    @Override
    public void addToCanvas() {
        for (int i = 0; i < gamePlayManager.getLives(); i++) {
            gameView.addImageToCanvas("life.png", position.getX() + (i*40), position.getY(), size, rotation);
        }

    }

    @Override
    public String toString() {
        return "Life: " + position;
    }
}
