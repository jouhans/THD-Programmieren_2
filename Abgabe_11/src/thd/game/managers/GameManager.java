package thd.game.managers;

import thd.game.level.Difficulty;
import thd.game.level.Level;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.movable.EnemyChopper;
import thd.gameobjects.movable.EnemyJet;
import thd.gameobjects.movable.Truck;

import java.util.List;

class GameManager extends LevelManager {

    private final List<GameObject> gameObjects;

    GameManager(GameView gameView) {
        super(gameView);
        startNewGame();
        gameObjects = gameObjectManager.getGameObjects();
    }

    private void gameManagement() {
        if (endOfGame()) {
            if (!overlay.isMessageShown()) {
                overlay.showMessage("Game Over");
            }
                if (gameView.timer(2000, 0, this)) {
                    overlay.stopShowing();
                    startNewGame();
            }
        } else if (endOfLevel()) {
            if (!overlay.isMessageShown()) {
                overlay.showMessage("Great Job");
            }
                if (gameView.timer(2000, 0, this)) {
                    overlay.stopShowing();
                    updatePointsAfterLevel();
                    switchToNextLevel();
                    initializeLevel();

            }
        }
    }

    @Override
    protected void initializeLevel() {
        overlay.showMessage(level.name, 2);
        super.initializeLevel();
    }

    @Override
    protected void initializeGame() {
        super.initializeGame();
        initializeLevel();
    }

    private void updatePointsAfterLevel() {
        for (GameObject gameObject : gameObjects) {
            if (gameObject instanceof Truck) {
                points += TRUCK_POINTS * level.number;
            }
        }
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
        boolean trucks = false;
        for (GameObject gameObject: gameObjects) {
            if (gameObject instanceof Truck) {
                trucks = true;
                break;
            }
        }
        return !trucks || lives == 0 || (!hasNextLevel() && endOfLevel());

    }

    private void startNewGame() {
        Level.difficulty = Difficulty.EASY;
        initializeGame();
    }

    @Override
    protected void gameLoop() {
        super.gameLoop();
        gameManagement();
    }
}
