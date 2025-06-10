package thd.game.managers;

import thd.game.utilities.GameView;

import java.awt.*;

/**
 * This class manages the graphical user interface.
 * <p>
 * This class initializes the {@link GameView} and connects it to the {@link GameManager}.
 * <p>
 * The GameLoop also starts with initialization.
 * To add Objects to the visible space refer to {@link GameManager}
 *
 * @see GameManager
 * @see GameView
 */
public class GameViewManager {
    private final GameManager gameManager;
    private final GameView gameView;

    /**
     * Initializes a new GameViewManager, sets up the {@link GameView} as well as the {@link GameManager}.
     * Initializes the game window with Title, Icon, etc.
     * <p>
     * Also starts the GameLoop at the end.
     *
     * @see GameView
     * @see GameManager
     */
    public GameViewManager() {
        gameView = new GameView();
        gameView.updateWindowTitle("Chopper Command");
        gameView.updateStatusText("Johannes Riedel - Java Programmierung SS 2025");
        gameView.updateWindowIcon("icon.png");
        gameView.updateBackgroundColor(Color.gray);
        gameManager = new GameManager(gameView);
        startGameLoop();
    }
    private void startGameLoop() {
        while (gameView.isVisible()) {
            gameManager.gameLoop();
            gameView.plotCanvas();
        }
    }
}
