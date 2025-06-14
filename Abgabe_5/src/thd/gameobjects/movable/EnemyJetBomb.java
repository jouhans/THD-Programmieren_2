package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;

/**
 * Creates a new enemy helicopter Object using {@link Position} for the Position and using {@link GameView} to display it.
 * <p>
 * This class provides methodes to update the Position and to add the Object to the GameView.
 *
 * @see Position
 * @see GameView
 */
class EnemyJetBomb extends GameObject {
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
        width = 150;
        height = 33;
        shotCorrectionXCoordinate = 20;
        shotCorrectionYCoordinate = 20;
        enemyJetBombMovementPattern = new EnemyJetBombMovementPattern();
        this.position.updateCoordinates(position.getX() + shotCorrectionXCoordinate, position.getY() + shotCorrectionYCoordinate);
        targetPosition.updateCoordinates(enemyJetBombMovementPattern.nextPosition(position));
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
}

