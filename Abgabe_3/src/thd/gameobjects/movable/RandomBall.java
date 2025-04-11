package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;

import java.awt.*;

/**
 * Creates a new RandomBall Object using {@link Position} for the Position and using {@link GameView} to display it.
 * <p>
 * This class provides methodes to update the Position and to add the Object to the GameView.
 *
 * @see Position
 * @see GameView
 */
public class RandomBall extends GameObject {

    private final RandomMovementPattern randomMovementPattern;
    private final QuadraticMovementPattern quadraticMovementPattern;

    /**
     * Initializes a new GameObject "RandomBall".
     *
     * @param gameView link GameObject to the current GameView
     */
    public RandomBall(GameView gameView) {
        super(gameView);
        randomMovementPattern = new RandomMovementPattern();
        position.updateCoordinates(randomMovementPattern.startPosition());
        speedInPixel = 4;
        size = 50.0;
        rotation = 0.0;
        width = 50;
        height = 50;
        quadraticMovementPattern = new QuadraticMovementPattern();
        targetPosition.updateCoordinates(quadraticMovementPattern.nextPosition());
    }

    @Override
    public void updatePosition() {
        if (gameView.timer(3000, 0, this)) {
            speedInPixel++;
        }
        if (gameView.timer(1000, 4000, this)) {
            if (position.similarTo(targetPosition)) {
                targetPosition.updateCoordinates(quadraticMovementPattern.nextPosition());
            }
            position.moveToPosition(targetPosition, speedInPixel);
        }

    }

    @Override
    public void addToCanvas() {
        if (gameView.gameTimeInMilliseconds() < 5000) {
            gameView.addOvalToCanvas(position.getX(), position.getY(), width, height, 0, true, Color.YELLOW);
        } else {
            gameView.addOvalToCanvas(position.getX(), position.getY(), width, height, 0, true, Color.RED);
        }
        gameView.addOvalToCanvas(targetPosition.getX(), targetPosition.getY(), width, height, 2, false, Color.WHITE);
    }

    @Override
    public String toString() {
        return "Random Ball: " + position;
    }
}

