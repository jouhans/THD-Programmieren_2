package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.MainCharacter;
import thd.gameobjects.base.Position;
import thd.gameobjects.unmovable.BackgroundGround;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates a new Player helicopter Object using {@link Position} for the Position and using {@link GameView} to display it.
 * <p>
 * This class provides methodes to update the Position and to add the Object to the GameView.
 *
 * @see Position
 * @see GameView
 */
public class PlayerChopper extends CollidingGameObject implements MainCharacter {
    private final List<CollidingGameObject> collidingGameObjectsForPathDecision;
    private int shotDurationInMilliseconds;
    enum State {MOVING_LEFT, MOVING_RIGHT};
    private State currentState;

    /**
     * Initializes a new GameObject "Player Helicopter".
     *
     * @param gameView link GameObject to the current GameView
     * @param gamePlayManager link GameObject to the GamePlayManager
     * @param backgroundGround link ground to the PlayerChopper
     */
    public PlayerChopper(GameView gameView, GamePlayManager gamePlayManager, BackgroundGround backgroundGround) {
        super(gameView, gamePlayManager);
        speedInPixel = 8;
        size = 2;
        rotation = 0.0;
        width = 122;
        height = 60;
        distanceToBackground = 4;
        position.updateCoordinates(new Position(640, 360));
        shotDurationInMilliseconds = gameView.gameTimeInMilliseconds();
        hitBoxOffsets(0, 0, 0, 0);
        collidingGameObjectsForPathDecision = new ArrayList<>();
        collidingGameObjectsForPathDecision.add(backgroundGround);
        currentState = State.MOVING_RIGHT;
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof EnemyChopperShot || other instanceof EnemyJetBomb || other instanceof EnemyChopper || other instanceof EnemyJet) {
            gamePlayManager.lifeLost();
        }
    }

    @Override
    public void addToCanvas() {
        switch (currentState) {
            case MOVING_LEFT -> gameView.addImageToCanvas("chopper.png", position.getX(), position.getY(), size, rotation);
            case MOVING_RIGHT -> gameView.addImageToCanvas("chopper_mirrored.png", position.getX(), position.getY(), size, rotation);
        }

    }

    @Override
    public void updateStatus() {
    }

    /**
     * Moves PlayerChopper to the left.
     */
    public void left() {
        currentState = State.MOVING_LEFT;
        if (position.getX() > (GameView.WIDTH / 2d) - 160) {
            position.left(speedInPixel); // Helikopter bewegt sich nach links
        } else {
            gamePlayManager.moveWorldToRight(speedInPixel); // Welt bewegt sich nach rechts
        }
    }

    /**
     * Moves PlayerChopper to the right.
     */
    public void right() {
        currentState = State.MOVING_RIGHT;
        if (position.getX() < (GameView.WIDTH / 2d) + 160) {
            position.right(speedInPixel); // Helikopter bewegt sich nach links
        } else {
            gamePlayManager.moveWorldToLeft(speedInPixel); // Welt bewegt sich nach rechts
        }
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
        for (CollidingGameObject collidingGameObject : collidingGameObjectsForPathDecision) {
            if (!collidesWith(collidingGameObject)) {
                position.moveToPosition(new Position(position.getX(), position.getY() + speedInPixel), speedInPixel);
                break;
            }
        }

    }

    @Override
    public void shoot() {
        switch (currentState) {
            case MOVING_LEFT -> {
                if (shotDurationInMilliseconds + 300 <= gameView.gameTimeInMilliseconds()) {
                    PlayerChopperShot playerChopperShot = new PlayerChopperShot(gameView, gamePlayManager, position, currentState);
                    gamePlayManager.spawnGameObject(playerChopperShot);
                    shotDurationInMilliseconds = gameView.gameTimeInMilliseconds();
                }
            }
            case MOVING_RIGHT -> {
                if (shotDurationInMilliseconds + 300 <= gameView.gameTimeInMilliseconds()) {
                    PlayerChopperShot playerChopperShot = new PlayerChopperShot(gameView, gamePlayManager, position, currentState);
                    gamePlayManager.spawnGameObject(playerChopperShot);
                    shotDurationInMilliseconds = gameView.gameTimeInMilliseconds();
                }
            }

        }
    }

    @Override
    public String toString() {
        return "Player Chopper: " + position;
    }
}

