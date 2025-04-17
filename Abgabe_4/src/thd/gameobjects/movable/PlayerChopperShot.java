package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;

/**
 * Creates a new PlayerChopper Shot Object using {@link Position} for the Position and using {@link GameView} to display it.
 * <p>
 * This class provides methodes to update the Position and to add the Object to the GameView.
 *
 * @see Position
 * @see GameView
 */
public class PlayerChopperShot extends GameObject {
    private final PlayerChopperShotMovementPattern playerChopperShotMovementPattern;

    /**
     * Initializes a new GameObject "PlayerChopperShot".
     *
     * @param gameView link GameObject to the current GameView
     */
    public PlayerChopperShot(GameView gameView) {
        super(gameView);
        speedInPixel = 4;
        size = 3.0;
        width = 200;
        height = 33;
        playerChopperShotMovementPattern = new PlayerChopperShotMovementPattern();
        position.updateCoordinates(playerChopperShotMovementPattern.startPosition());
        targetPosition.updateCoordinates(playerChopperShotMovementPattern.nextPosition(position));
    }

    @Override
    public void updatePosition() {
        if (!targetPosition.similarTo(position)) {
            position.moveToPosition(targetPosition, speedInPixel);
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(ChopperBlockImages.PLAYER_CHOPPER_SHOT, position.getX(), position.getY(), size, rotation);
    }

    @Override
    public String toString() {
        return "Player Chopper Shot: " + position;
    }
}

