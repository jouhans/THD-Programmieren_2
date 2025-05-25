package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.base.ShiftableGameObject;

import java.awt.*;

/**
 * Creates a new BackgroundAir Object using {@link Position} for the Position and using {@link GameView} to display it.
 * <p>
 * This class provides methodes to update the Position and to add the Object to the GameView.
 *
 * @see Position
 * @see GameView
 */
public class BackgroundAir extends CollidingGameObject implements ShiftableGameObject {
    /**
     * Initializes a new GameObject "BackgroundAir".
     *
     * @param gameView link GameObject to the current GameView
     * @param gamePlayManager link GameObject to the GamePlayManager
     * @param position set a position to spawn
     */
    public BackgroundAir(GameView gameView, GamePlayManager gamePlayManager, Position position) {
        super(gameView, gamePlayManager);
        size = 0.9;
        miniMapsize = 0.18;
        width = 640;
        height = 175;
        distanceToBackground = 0;
        this.position.updateCoordinates(new Position(position.getX(), position.getY()));
        hitBoxOffsets(0, 0, 0, 0);
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {}

    @Override
    public void updatePosition() {
        miniMapPosition = calculatePositionOnMinimap(position);
    }

    @Override
    public void updateCoordinates(Position position) {
        this.position.updateCoordinates(position);
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("background_air.png", position.getX(), position.getY(), size, rotation);
    }

    @Override
    public String toString() {
        return "BackgroundAir: " + position;
    }
}

