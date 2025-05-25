package thd.gameobjects.movable;

import thd.game.level.Level;
import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.ActivatableGameObject;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.base.ShiftableGameObject;

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
public class EnemyChopper extends CollidingGameObject implements ShiftableGameObject, ActivatableGameObject<EnemyChopper> {
    private int shotDurationInMilliseconds;
    private Random random;
    private enum State {MOVING_LEFT, MOVING_RIGHT}
    private State currentState;
    private boolean active;
    private int minEnemyChopperShotRate;


    /**
     * Initializes a new GameObject "EnemyChopper".
     *
     * @param gameView link GameObject to the current GameView
     * @param gamePlayManager link GameObject to the GamePlayManager
     */
    public EnemyChopper(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        switch (Level.difficulty) {
            case STANDARD -> minEnemyChopperShotRate = 2;
            case EASY -> minEnemyChopperShotRate = 4;
            case HARD -> minEnemyChopperShotRate = 1;
            case IMPOSSIBLE -> minEnemyChopperShotRate = 0;
        }
        switch (Level.difficulty) {
            case STANDARD -> speedInPixel = 3;
            case EASY -> speedInPixel = 2;
            case HARD -> speedInPixel = 4;
            case IMPOSSIBLE -> speedInPixel = 6;
        }
        size = 1.0;
        width = 85;
        height = 50;
        distanceToBackground = 4;
        position.updateCoordinates(new Position(0, 200));
        shotDurationInMilliseconds = gameView.gameTimeInMilliseconds();
        random = new Random();
        hitBoxOffsets(0, 0, 0, 0);
        currentState = State.MOVING_RIGHT;
        active = false;
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof PlayerChopperShot || other instanceof PlayerChopper) {
            gamePlayManager.destroyGameObject(this);
            gamePlayManager.addPoints(GamePlayManager.ENEMY_CHOPPER_POINTS);
        }
    }

    @Override
    public void updatePosition() {
        if (gameView.timer(2000, 2000, this)) {
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
        shoot();
    }

    private void shoot() {
        int randomNumber = random.nextInt(3) + minEnemyChopperShotRate;
        if (shotDurationInMilliseconds + (randomNumber * 1000) <= gameView.gameTimeInMilliseconds()) {
            gamePlayManager.spawnGameObject(new EnemyChopperShot(gameView, gamePlayManager, position));
            shotDurationInMilliseconds = gameView.gameTimeInMilliseconds();
        }
    }

    @Override
    public void addToCanvas() {
        switch (currentState) {
            case MOVING_LEFT -> gameView.addImageToCanvas("enemychopper.png", position.getX(), position.getY(), size, rotation);
            case MOVING_RIGHT -> gameView.addImageToCanvas("enemychopper_mirrored.png", position.getX(), position.getY(), size, rotation);
        }
        if (isVisibleOnMinimap(position, width)) {
            gameView.addRectangleToCanvas(miniMapPosition.getX(), miniMapPosition.getY(), 10, 10, 0, true, Color.red);
        }
    }

    @Override
    public String toString() {
        return "Enemy Chopper: " + position;
    }

    @Override
    public boolean tryToActivate(EnemyChopper info) {
        return !(info.position.getX() < 0) && !(info.position.getX() > 1280) && !(info.position.getY() < 0) && !(info.position.getY() > 720);
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

