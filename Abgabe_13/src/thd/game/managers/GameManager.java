package thd.game.managers;

import thd.game.level.Difficulty;
import thd.game.level.Level;
import thd.game.utilities.FileAccess;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.movable.EnemyChopper;
import thd.gameobjects.movable.EnemyJet;
import thd.gameobjects.movable.Truck;
import thd.screens.GameInfo;
import thd.screens.Screens;

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
            if (lives == 0) {
                gameView.playSound("gamewin.wav", false);
                gameView.stopAllSounds();
                overlay.stopShowing();
                Screens.showEndScreen(gameView, "Game Over. You got " + points + " Points.");
                startNewGame();
            } else {
                gameView.playSound("gamewin.wav", false);
                gameView.stopAllSounds();
                overlay.stopShowing();
                points *= lives;
                Screens.showEndScreen(gameView, "You win. You got " + points + " Points.");
                startNewGame();
            }
        } else if (endOfLevel()) {
            if (!overlay.isMessageShown()) {
                gameView.playSound("levelpassed.wav", false);
                overlay.showMessage("Great Job");
            }
            if (gameView.timer(4000, 0, this)) {
                gameView.stopAllSounds();
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
        for (GameObject gameObject : gameObjects) {
            if (gameObject instanceof EnemyJet || gameObject instanceof EnemyChopper) {
                return false;
            }
        }
        for (GameObject gameObject : activatableGameObjects) {
            if (gameObject instanceof EnemyJet || gameObject instanceof EnemyChopper) {
                return false;
            }
        }
        return true;
    }

    private boolean endOfGame() {
        boolean trucks = false;
        for (GameObject gameObject : gameObjects) {
            if (gameObject instanceof Truck) {
                trucks = true;
                break;
            }
        }
        return !trucks || lives == 0 || (!hasNextLevel() && endOfLevel());

    }

    private void startNewGame() {
        Level.difficulty = FileAccess.readDifficultyFromDisc();
        String setDifficultyString = Screens.showStartScreen(gameView, GameInfo.TITLE, GameInfo.DESCRIPTION, Level.difficulty.name);
        Level.difficulty = Difficulty.fromName(setDifficultyString);
        FileAccess.writeDifficultyToDisc(Level.difficulty);

        initializeGame();
    }

    @Override
    protected void gameLoop() {
        super.gameLoop();
        gameManagement();
    }
}
