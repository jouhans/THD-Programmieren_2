package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Position;

import java.util.List;

/**
 * Creates a new Ground Object using {@link Position} for the Position and using {@link GameView} to display it.
 * <p>
 * This class provides methodes to update the Position and to add the Object to the GameView.
 *
 * @see Position
 * @see GameView
 */
public class Ground extends CollidingGameObject {
    private List<CollidingGameObject> collidingGameObjectsForPathDecision;
    /**
     * Initializes a new GameObject "Ground".
     *
     * @param gameView link GameObject to the current GameView
     * @param gamePlayManager link GameObject to the GamePlayManager
     */
    public Ground(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        size = 2.0;
        width = 1280;
        height = 175;
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
    }

    @Override
    public String toString() {
        return "Ground: " + position;
    }
}
