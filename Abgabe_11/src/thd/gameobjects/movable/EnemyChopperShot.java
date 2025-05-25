package thd.gameobjects.movable;

import thd.game.level.Level;
import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.base.ShiftableGameObject;

import java.util.Random;

/**
 * Creates a new EnemyChopper Shot Object using {@link Position} for the Position and using {@link GameView} to display it.
 * <p>
 * This class provides methodes to update the Position and to add the Object to the GameView.
 *
 * @see Position
 * @see GameView
 */
class EnemyChopperShot extends CollidingGameObject implements ShiftableGameObject {
    private final int shotCorrectionYCoordinate;
    private final int shotCorrectionXCoordinate;
    private final Random random;
    private final int timeTillExplode;

    /**
     * Initializes a new GameObject "EnemyChopperShot".
     *
     * @param gameView        link GameObject to the current GameView
     * @param gamePlayManager link GameObject to the GamePlayManager
     * @param position        get current Position of related GameObject
     */
    protected EnemyChopperShot(GameView gameView, GamePlayManager gamePlayManager, Position position) {
        super(gameView, gamePlayManager);
        switch (Level.difficulty) {
            case STANDARD -> speedInPixel = 6;
            case EASY -> speedInPixel = 4;
            case HARD -> speedInPixel = 7;
            case IMPOSSIBLE -> speedInPixel = 9;
        }
        size = 0.4;
        width = 28;
        height = 25;
        distanceToBackground = 4;
        shotCorrectionXCoordinate = 12;
        shotCorrectionYCoordinate = 12;
        this.position.updateCoordinates(position.getX() - shotCorrectionXCoordinate, position.getY() + shotCorrectionYCoordinate);
        hitBoxOffsets(0, 0, 0, 0);
        timeAtStart = gameView.gameTimeInMilliseconds();
        random = new Random();
        timeTillExplode = (random.nextInt(1, 400)) * 10;
    }
    @Override
    public void updatePosition() {
        if (gameView.gameTimeInMilliseconds() < timeAtStart + timeTillExplode) {
            position.right(speedInPixel);
        } else {
            gamePlayManager.destroyGameObject(this);
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("enemychoppershot.png", position.getX(), position.getY(), size, rotation);
    }

    @Override
    public String toString() {
        return "Enemy Chopper Shot: " + position;
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof PlayerChopper || other instanceof PlayerChopperShot || other instanceof EnemyJetBomb) {
            gamePlayManager.destroyGameObject(this);
        }
    }
}

