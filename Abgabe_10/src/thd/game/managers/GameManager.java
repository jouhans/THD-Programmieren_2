package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.movable.EnemyChopper;
import thd.gameobjects.movable.EnemyJet;

import java.util.List;

class GameManager extends LevelManager {

    private final List<GameObject> gameObjects;

    GameManager(GameView gameView) {
        super(gameView);
        initializeGame();
        gameObjects = gameObjectManager.getGameObjects();
    }

    private void gameManagement() {
        if (endOfGame()) {
            initializeGame();
        } else if (endOfLevel()) {
            switchToNextLevel();
            initializeLevel();
        }
    }

    @Override
    protected void initializeLevel() {
        super.initializeLevel();
    }

    @Override
    protected void initializeGame() {
        super.initializeGame();
        initializeLevel();
    }

    private boolean endOfLevel() {
        for (GameObject gameObject: gameObjects) {
            if (gameObject instanceof EnemyJet || gameObject instanceof EnemyChopper) {
                return false;
            }
        }
        for (GameObject gameObject: activatableGameObjects) {
            if (gameObject instanceof EnemyJet || gameObject instanceof EnemyChopper) {
                return false;
            }
        }
        return true;
    }

    private boolean endOfGame() {
        return lives == 0 || (!hasNextLevel() && endOfLevel());
    }

    @Override
    protected void gameLoop() {
        super.gameLoop();
        gameManagement();
    }
}
