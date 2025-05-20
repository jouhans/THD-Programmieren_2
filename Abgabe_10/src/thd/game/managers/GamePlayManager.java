package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;


/**
 * This class manages the gameplay, including the creation and destruction
 * of game objects and the main game loop.
 * <p>
 * It extends {@link UserControlledGameObjectPool} and interacts with the
 * {@link GameObjectManager} to control the state of game objects.
 *
 * @see GameManager
 * @see GameView
 */
public class GamePlayManager extends WorldShiftManager {

    /**
     * Points for killing the enemy Chopper.
     */
    public static final int ENEMY_CHOPPER_POINTS = 100;
    /**
     * Points for killing the enemy Jet.
     */
    public static final int ENEMY_JET_POINTS = 200;

    protected final GameObjectManager gameObjectManager;
    protected int points;
    protected int lives;


    protected GamePlayManager(GameView gameView) {
        super(gameView);
        gameObjectManager = new GameObjectManager();
    }

    /**
     * Adds a game object to the GameView.
     *
     * @param gameObject The gameObject to be added.
     */
    @Override
    public void spawnGameObject(GameObject gameObject){
        super.spawnGameObject(gameObject);
        gameObjectManager.add(gameObject);
    }

    /**
     * Removes a specific game object from the GameView.
     *
     * @param gameObject The gameObject to be removed.
     */
    @Override
    public void destroyGameObject(GameObject gameObject) {
        super.destroyGameObject(gameObject);
        gameObjectManager.remove(gameObject);
    }

    /**
     * Get the current number of Lives.
     *
     * @return lives
     */
    public int getLives() {
        return lives;
    }

    /**
     * Reduce the lives by 1.
     */
    public void lifeLost() {
        if (lives > 0) {
            lives -= 1;
        }
    }

    /**
     * Add Points to the Score.
     *
     * @param numberOfPoints Points added to the Score
     */
    public void addPoints(int numberOfPoints) {
        points += numberOfPoints;
    }

    /**
     * Get the current Score Points.
     *
     * @return points
     */
    public int getPoints() {
        return points;
    }

    @Override
    protected void destroyAllGameObjects(){
        super.destroyAllGameObjects();
        gameObjectManager.removeAll();
    }
    private void gamePlayManagement(){
        if (lives == 0) {
            destroyGameObject(playerChopper);
        }
    }

    @Override
    protected void gameLoop() {
        super.gameLoop();
        gameObjectManager.gameLoop();
        gamePlayManagement();
    }
}
