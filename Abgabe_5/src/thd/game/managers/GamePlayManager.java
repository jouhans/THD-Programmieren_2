package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.movable.Square;


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
public class GamePlayManager extends UserControlledGameObjectPool{

    private final GameObjectManager gameObjectManager;
    private int currentNumberOfVisibleSquares;

    protected GamePlayManager(GameView gameView) {
        super(gameView);
        gameObjectManager = new GameObjectManager();
        currentNumberOfVisibleSquares = 0;
    }

    /**
     * Adds a game object to the GameView.
     *
     * @param gameObject The gameObject to be added.
     */
    public void spawnGameObject(GameObject gameObject){
        gameObjectManager.add(gameObject);
    }

    /**
     * Removes a specific game object from the GameView.
     *
     * @param gameObject The gameObject to be removed.
     */
    public void destroyGameObject(GameObject gameObject) {
        gameObjectManager.remove(gameObject);
    }

    protected void destroyAllGameObjects(){
        gameObjectManager.removeAll();
    }

    private void gamePlayManagement(){
        if (currentNumberOfVisibleSquares < 5 && gameView.timer(1000, 0, this)) {
            spawnGameObject(new Square(gameView, this));
            currentNumberOfVisibleSquares++;
        }



    }

    @Override
    protected void gameLoop() {
        super.gameLoop();
        gameObjectManager.gameLoop();
        gamePlayManagement();
    }
}
