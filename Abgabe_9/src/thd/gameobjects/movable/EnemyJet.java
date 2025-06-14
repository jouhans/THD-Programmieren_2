package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.ActivatableGameObject;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.base.ShiftableGameObject;
import thd.gameobjects.unmovable.BackgroundGround;

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
    private MovingState movingState;

    private enum MovingState {MOVE_STATE1, MOVE_STATE2, MOVE_STATE3}


    /**
     * Initializes a new GameObject "EnemyChopper".
     *
     * @param gameView link GameObject to the current GameView
     * @param gamePlayManager link GameObject to the GamePlayManager
     * @param backgroundGround link ground to the EnemyJet
     */
    public EnemyJet(GameView gameView, GamePlayManager gamePlayManager, BackgroundGround backgroundGround) {
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
        movingState = MovingState.MOVE_STATE1;
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof PlayerChopperShot || other instanceof PlayerChopper) {
            gamePlayManager.destroyGameObject(this);
            gamePlayManager.addPoints(GamePlayManager.ENEMY_JET_POINTS);
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
            case MOVING_LEFT -> {
                gameView.addImageToCanvas("enemyjet.png", position.getX(), position.getY(), size, rotation);
                switch (movingState) {
                    case MOVE_STATE1 -> {
                        gameView.addImageToCanvas("enemyjet_moveanimation1.png", position.getX() + 80, position.getY() + 10, 2, rotation);
                        if (gameView.timer(80, 0, this)) {
                            switchmovingState();
                        }
                    }
                    case MOVE_STATE2 -> {
                        gameView.addImageToCanvas("enemyjet_moveanimation2.png", position.getX() + 80, position.getY() + 10, 2, rotation);
                        if (gameView.timer(80, 0, this)) {
                            switchmovingState();
                        }
                    }
                    case MOVE_STATE3 -> {
                        gameView.addImageToCanvas("enemyjet_moveanimation3.png", position.getX() + 80, position.getY() + 10, 2, rotation);
                        if (gameView.timer(80, 0, this)) {
                            switchmovingState();
                        }
                    }
                }
            }
            case MOVING_RIGHT -> {
                gameView.addImageToCanvas("enemyjet_mirrored.png", position.getX(), position.getY(), size, rotation);
                switch (movingState) {
                    case MOVE_STATE1 -> {
                        gameView.addImageToCanvas("enemyjet_moveanimation1_mirrored.png", position.getX() - 20, position.getY() + 10, 2, rotation);
                        if (gameView.timer(80, 0, this)) {
                            switchmovingState();
                        }
                    }
                    case MOVE_STATE2 -> {
                        gameView.addImageToCanvas("enemyjet_moveanimation2_mirrored.png", position.getX() - 20, position.getY() + 10, 2, rotation);
                        if (gameView.timer(80, 0, this)) {
                            switchmovingState();
                        }
                    }
                    case MOVE_STATE3 -> {
                        gameView.addImageToCanvas("enemyjet_moveanimation3_mirrored.png", position.getX() - 20, position.getY() + 10, 2, rotation);
                        if (gameView.timer(80, 0, this)) {
                            switchmovingState();
                        }
                    }
                }
            }
        }


    }

    private void switchmovingState() {
        int nextState = (movingState.ordinal() + 1) % EnemyJet.MovingState.values().length;
        movingState = EnemyJet.MovingState.values()[nextState];
    }

    @Override
    public String toString() {
        return "Enemy Jet: " + position;
    }

    @Override
    public boolean tryToActivate(EnemyJet info) {
        EnemyJet tryToActivate = (EnemyJet) info;

        return !(tryToActivate.position.getX() < 0) && !(tryToActivate.position.getX() > 1280) && !(tryToActivate.position.getY() < 0) && !(tryToActivate.position.getY() > 720);
    }
}

