package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;

import java.awt.*;
import java.util.Random;

/**
 * Creates a new enemy helicopter Object using {@link Position} for the Position and using {@link GameView} to display it.
 * <p>
 * This class provides methodes to update the Position and to add the Object to the GameView.
 *
 * @see Position
 * @see GameView
 */
public class EnemyJet extends CollidingGameObject implements ShiftableGameObject, ActivatableGameObject<EnemyJet> {
    private int shotDurationInMilliseconds;
    private final Random random;
    private enum State {MOVING_LEFT, MOVING_RIGHT};
    private State currentState;
    private boolean active;


    /**
     * Initializes a new GameObject "EnemyChopper".
     *
     * @param gameView link GameObject to the current GameView
     * @param gamePlayManager link GameObject to the GamePlayManager
     */
    public EnemyJet(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        random = new Random();
        speedInPixel = 4;
        size = 2.0;
        width = 80;
        height = 30;
        distanceToBackground = 4;
        position.updateCoordinates(new Position(300.0 + random.nextDouble(350), 100.0 + random.nextDouble(50)));
        shotDurationInMilliseconds = gameView.gameTimeInMilliseconds();
        hitBoxOffsets(0, 0, 0, 0);
        currentState = State.MOVING_RIGHT;
        miniMapPosition = calculatePositionOnMinimap(position);
        active = false;
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof PlayerChopperShot) {
            gamePlayManager.destroyGameObject(this);
            gamePlayManager.addPoints(GamePlayManager.ENEMY_JET_POINTS);
        } else if (other instanceof PlayerChopper) {
            gamePlayManager.destroyGameObject(this);
        }
    }

    @Override
    public void updatePosition() {
        if (gameView.timer(3000, 3000, this)) {
            currentState = State.MOVING_RIGHT;
            position.right(speedInPixel);
        } else {
            currentState = State.MOVING_LEFT;
            position.left(speedInPixel);
        }
        miniMapPosition = calculatePositionOnMinimap(position);
    }

    @Override
    public void updateStatus() {
        int randomNumber = random.nextInt(3) + 3;
        if (shotDurationInMilliseconds + (randomNumber * 1000) <= gameView.gameTimeInMilliseconds()) {
            drop();
        }
    }

    private void drop() {
        gamePlayManager.spawnGameObject(new EnemyJetBomb(gameView, gamePlayManager, position));
        shotDurationInMilliseconds = gameView.gameTimeInMilliseconds();
    }

    @Override
    public void addToCanvas() {
        switch (currentState) {
            case MOVING_LEFT -> gameView.addImageToCanvas("enemyjet.png", position.getX(), position.getY(), size, rotation);
            case MOVING_RIGHT -> gameView.addImageToCanvas("enemyjet_mirrored.png", position.getX(), position.getY(), size, rotation);
        }
        if (isVisibleOnMinimap(position, width)) {
            gameView.addRectangleToCanvas(miniMapPosition.getX(), miniMapPosition.getY(), 10, 10, 0, true, Color.gray);
        }
    }

    @Override
    public String toString() {
        return "Enemy Jet: " + position;
    }

    @Override
    public boolean tryToActivate(EnemyJet info) {
        return !(position.getX() < 0) && !(position.getX() > 1280) || !(position.getY() < 0) && !(position.getY() > 720 && info != null);
    }

    @Override
    public void deactivate() {
        active = false;
    }

    @Override
    public void activate() {
        active = true;
    }

    @Override
    public boolean isActive() {
        return active;
    }
}

