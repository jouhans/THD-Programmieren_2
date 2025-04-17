package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;

import java.awt.*;

/**
 * Creates a new Player helicopter Object using {@link Position} for the Position and using {@link GameView} to display it.
 * <p>
 * This class provides methodes to update the Position and to add the Object to the GameView.
 *
 * @see Position
 * @see GameView
 */
public class PlayerChopper extends GameObject {
    private boolean shotInProgress;

    /**
     * Initializes a new GameObject "Player Helicopter".
     *
     * @param gameView link GameObject to the current GameView
     */
    public PlayerChopper(GameView gameView) {
        super(gameView);
        shotInProgress = false;
        speedInPixel = 10;
        size = 2;
        rotation = 0.0;
        width = 150;
        height = 33;
        position.updateCoordinates(new Position(640, 360));
    }

    @Override
    public void addToCanvas() {
        if (shotInProgress) {
            gameView.addTextToCanvas("X", position.getX(), position.getY(), 30, true, Color.WHITE, rotation, "pressstart2pregular.ttf");
        } else {
            gameView.addImageToCanvas("chopper.png", position.getX(), position.getY(), size, rotation);
        }
        shotInProgress = false;
    }

    @Override
    public void updateStatus() {
        if (gameView.timer(5000, 0, this)) {
            size++;
        }
    }

    /**
     * Moves PlayerChopper to the left.
     */
    public void left() {
        position.moveToPosition(new Position(position.getX() - speedInPixel, position.getY()), speedInPixel);
    }

    /**
     * Moves PlayerChopper to the right.
     */
    public void right() {
        position.moveToPosition(new Position(position.getX() + speedInPixel, position.getY()), speedInPixel);
    }

    /**
     * Moves PlayerChopper up.
     */
    public void up() {
        position.moveToPosition(new Position(position.getX(), position.getY() - speedInPixel), speedInPixel);
    }

    /**
     * Moves PlayerChopper down.
     */
    public void down() {
        position.moveToPosition(new Position(position.getX(), position.getY() + speedInPixel), speedInPixel);
    }

    /**
     * Let PlayerChopper shoot.
     */
    public void shoot() {
        shotInProgress = true;
    }

    @Override
    public String toString() {
        return "Player Chopper: " + position;
    }
}

