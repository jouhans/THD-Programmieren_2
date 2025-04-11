package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;

import java.awt.*;

/**
 * Creates a new FollowerBall Object using {@link Position} for the Position and using {@link GameView} to display it.
 * <p>
 * This class provides methodes to update the Position and to add the Object to the GameView.
 *
 * @see Position
 * @see GameView
 */
public class FollowerBall extends GameObject {

    private final RandomBall followMe;

    /**
     * Initializes a new GameObject "RandomBall".
     *
     * @param gameView link GameObject to the current GameView
     * @param followMe link RandomBall with the FollowerBall
     */
    public FollowerBall(GameView gameView, RandomBall followMe) {
        super(gameView);
        position.updateCoordinates(new Position(0, 0));
        speedInPixel = 3;
        size = 50.0;
        rotation = 0.0;
        width = 50;
        height = 50;
        this.followMe = followMe;
        targetPosition.updateCoordinates(this.followMe.getPosition());
    }

    @Override
    public void updatePosition() {
        targetPosition.updateCoordinates(this.followMe.getPosition());
        position.moveToPosition(targetPosition, speedInPixel);
    }

    @Override
    public void addToCanvas() {
        gameView.addOvalToCanvas(position.getX(), position.getY(), width, height, 0, true, Color.GREEN);
    }

    @Override
    public String toString() {
        return "Follower Ball: " + position;
    }
}