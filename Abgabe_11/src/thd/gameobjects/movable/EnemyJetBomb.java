package thd.gameobjects.movable;

import thd.game.level.Level;
import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.base.ShiftableGameObject;
import thd.gameobjects.unmovable.BackgroundGround;

import java.awt.*;

/**
 * Creates a new enemy helicopter Object using {@link Position} for the Position and using {@link GameView} to display it.
 * <p>
 * This class provides methodes to update the Position and to add the Object to the GameView.
 *
 * @see Position
 * @see GameView
 */
class EnemyJetBomb extends CollidingGameObject implements ShiftableGameObject {
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
        switch (Level.difficulty) {
            case STANDARD -> speedInPixel = 4;
            case EASY -> speedInPixel = 2;
            case HARD -> speedInPixel = 5;
            case IMPOSSIBLE -> speedInPixel = 8;
        }
        size = 2.0;
        width = 16;
        height = 35;
        distanceToBackground = 4;
        shotCorrectionXCoordinate = 20;
        shotCorrectionYCoordinate = 20;
        this.position.updateCoordinates(position.getX() + shotCorrectionXCoordinate, position.getY() + shotCorrectionYCoordinate);
        hitBoxOffsets(0, 0, 0, 0);
        miniMapPosition = calculatePositionOnMinimap(position);
    }

    @Override
    public void updatePosition() {
        position.down(speedInPixel);
        miniMapPosition = calculatePositionOnMinimap(position);
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("enemyjetbomb.png", position.getX(), position.getY(), size, rotation);
        if (isVisibleOnMinimap(position, width)) {
            gameView.addRectangleToCanvas(miniMapPosition.getX(), miniMapPosition.getY(), 5, 15, 0, true, Color.blue);
        }
    }

    @Override
    public String toString() {
        return "Enemy Jet Bomb: " + position;
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof PlayerChopperShot || other instanceof PlayerChopper || other instanceof BackgroundGround) {
            gamePlayManager.destroyGameObject(this);
            gameView.playSound("enemyjetbombexplosion.wav", false);
        }
    }
}

