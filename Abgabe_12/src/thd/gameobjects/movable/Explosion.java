package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.base.ShiftableGameObject;

/**
 * Creates a new Explosion Object using {@link Position} for the Position
 * and using {@link GameView} to display it.
 * <p>
 * This class provides methods to update the Position and to add the Object to
 * the GameView.
 *
 * @see Position
 * @see GameView
 */
class Explosion extends GameObject implements ShiftableGameObject {

    private ExplosionState explosionState;

    private enum ExplosionState {
        EXPLOSION_STATE1, EXPLOSION_STATE2, EXPLOSION_STATE3, EXPLOSION_STATE4, EXPLOSION_STATE5, EXPLOSION_STATE6
    }

    /**
     * Initializes a new GameObject "EnemyChopper".
     *
     * @param gameView        GameView to show the game object on.
     * @param gamePlayManager link GameObject to the GamePlayManager
     * @param position        set the position
     */
    Explosion(GameView gameView, GamePlayManager gamePlayManager, Position position) {
        super(gameView, gamePlayManager);
        size = 2.0;
        width = 10;
        height = 10;
        distanceToBackground = 6;
        explosionState = ExplosionState.EXPLOSION_STATE1;
        this.position.updateCoordinates(new Position(position.getX(), position.getY() - 5));
    }

    @Override
    public void addToCanvas() {
        switch (explosionState) {
            case EXPLOSION_STATE1 -> {
                gameView.addImageToCanvas("explosion_animation1.png", position.getX(), position.getY(), size, rotation);
                if (gameView.timer(80, 0, this)) {
                    switchexplosionState();
                }
            }
            case EXPLOSION_STATE2 -> {
                gameView.addImageToCanvas("explosion_animation2.png", position.getX(), position.getY(), size, rotation);
                if (gameView.timer(80, 0, this)) {
                    switchexplosionState();
                }
            }
            case EXPLOSION_STATE3 -> {
                gameView.addImageToCanvas("explosion_animation3.png", position.getX(), position.getY(), size, rotation);
                if (gameView.timer(80, 0, this)) {
                    switchexplosionState();
                }
            }
            case EXPLOSION_STATE4 -> {
                gameView.addImageToCanvas("explosion_animation4.png", position.getX(), position.getY(), size, rotation);
                switchexplosionState();
            }
            case EXPLOSION_STATE5 -> {
                gameView.addImageToCanvas("explosion_animation5.png", position.getX(), position.getY(), size, rotation);
                if (gameView.timer(80, 0, this)) {
                    switchexplosionState();
                }
            }
            case EXPLOSION_STATE6 -> {
                gameView.addImageToCanvas("explosion_animation6.png", position.getX(), position.getY(), size, rotation);
                if (gameView.timer(80, 0, this)) {
                    gamePlayManager.destroyGameObject(this);
                }
            }
        }
    }

    private void switchexplosionState() {
        int nextState = (explosionState.ordinal() + 1) % ExplosionState.values().length;
        explosionState = ExplosionState.values()[nextState];
    }
}
