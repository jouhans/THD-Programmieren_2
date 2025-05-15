package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.unmovable.Ground;

/**
 * Creates a new enemy helicopter Object using {@link Position} for the Position and using {@link GameView} to display it.
 * <p>
 * This class provides methodes to update the Position and to add the Object to the GameView.
 *
 * @see Position
 * @see GameView
 */
class EnemyJetBomb extends CollidingGameObject {
    private final EnemyJetBombMovementPattern enemyJetBombMovementPattern;
    private final int shotCorrectionYCoordinate;
    private final int shotCorrectionXCoordinate;

    /**
     * Initializes a new GameObject "EnemyChopper".
     *
     * @param gameView link GameObject to the current GameView
     * @param gamePlayManager link GameObject to the GamePlayManager
     * @param position get current Position of related GameObject
     */
    protected EnemyJetBomb(GameView gameView, GamePlayManager gamePlayManager, Position position) {
        super(gameView, gamePlayManager);
        speedInPixel = 4;
        size = 2.0;
        width = 16;
        height = 35;
        shotCorrectionXCoordinate = 20;
        shotCorrectionYCoordinate = 20;
        enemyJetBombMovementPattern = new EnemyJetBombMovementPattern();
        this.position.updateCoordinates(position.getX() + shotCorrectionXCoordinate, position.getY() + shotCorrectionYCoordinate);
        targetPosition.updateCoordinates(enemyJetBombMovementPattern.nextPosition(position));
        hitBoxOffsets(0, 0, 0, 0);
    }

    @Override
    public void updatePosition() {
        if (!targetPosition.similarTo(position)) {
            position.moveToPosition(targetPosition, speedInPixel);
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("enemyjetbomb.png", position.getX(), position.getY(), size, rotation);
    }

    @Override
    public String toString() {
        return "Enemy Jet Bomb: " + position;
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof PlayerChopperShot || other instanceof EnemyChopperShot || other instanceof EnemyChopper || other instanceof PlayerChopper || other instanceof EnemyJetBomb || other instanceof Ground) {
            gamePlayManager.destroyGameObject(this);
        }
    }
}

