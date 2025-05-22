package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.ActivatableGameObject;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.base.ShiftableGameObject;

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


    /**
     * Initializes a new GameObject "EnemyChopper".
     *
     * @param gameView link GameObject to the current GameView
     * @param gamePlayManager link GameObject to the GamePlayManager
     */
    public EnemyChopper(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        speedInPixel = 3;
        size = 1.0;
        width = 85;
        height = 50;
        distanceToBackground = 4;
        position.updateCoordinates(new Position(0, 200));
        shotDurationInMilliseconds = gameView.gameTimeInMilliseconds();
        random = new Random();
        hitBoxOffsets(0, 0, 0, 0);
        currentState = State.MOVING_RIGHT;
    }

    /**
     * Initializes a new GameObject "EnemyChopper".
     *
     * @param gameView link GameObject to the current GameView
     * @param gamePlayManager link GameObject to the GamePlayManager
     * @param position set a start Position
     */
    public EnemyChopper(GameView gameView, GamePlayManager gamePlayManager, Position position) {
        this(gameView, gamePlayManager);
        speedInPixel = 3;
        size = 1.0;
        width = 85;
        height = 50;
        distanceToBackground = 4;
        position.updateCoordinates(position);
        shotDurationInMilliseconds = gameView.gameTimeInMilliseconds();
        random = new Random();
        hitBoxOffsets(0, 0, 0, 0);
        currentState = State.MOVING_RIGHT;
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
    }

    @Override
    public void updateStatus() {
        shoot();
    }

    private void shoot() {
        int randomNumber = random.nextInt(3) + 2;
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

    }

    @Override
    public String toString() {
        return "Enemy Chopper: " + position;
    }

    @Override
    public boolean tryToActivate(EnemyChopper info) {
        EnemyChopper tryToActivate = (EnemyChopper) info;

        return !(tryToActivate.position.getX() < 0) && !(tryToActivate.position.getX() > 1280) && !(tryToActivate.position.getY() < 0) && !(tryToActivate.position.getY() > 720);
    }
}

