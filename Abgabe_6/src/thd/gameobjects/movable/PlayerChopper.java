package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.MainCharacter;
import thd.gameobjects.base.Position;

/**
 * Creates a new Player helicopter Object using {@link Position} for the Position and using {@link GameView} to display it.
 * <p>
 * This class provides methodes to update the Position and to add the Object to the GameView.
 *
 * @see Position
 * @see GameView
 */
public class PlayerChopper extends CollidingGameObject implements MainCharacter {

    private int shotDurationInMilliseconds;

    /**
     * Initializes a new GameObject "Player Helicopter".
     *
     * @param gameView link GameObject to the current GameView
     * @param gamePlayManager link GameObject to the GamePlayManager
     */
    public PlayerChopper(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        speedInPixel = 8;
        size = 2;
        rotation = 0.0;
        width = 150;
        height = 33;
        position.updateCoordinates(new Position(640, 360));
        shotDurationInMilliseconds = gameView.gameTimeInMilliseconds();
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {

    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("chopper.png", position.getX(), position.getY(), size, rotation);
    }

    @Override
    public void updateStatus() {
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

    @Override
    public void shoot() {
        if (shotDurationInMilliseconds + 300 <= gameView.gameTimeInMilliseconds()) {
            PlayerChopperShot playerChopperShot = new PlayerChopperShot(gameView, gamePlayManager, position);
            gamePlayManager.spawnGameObject(playerChopperShot);
            shotDurationInMilliseconds = gameView.gameTimeInMilliseconds();
        }
    }

    @Override
    public String toString() {
        return "Player Chopper: " + position;
    }
}

