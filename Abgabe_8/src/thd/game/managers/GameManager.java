package thd.game.managers;

import thd.game.managers.GameWorldManager;
import thd.game.utilities.GameView;
class GameManager extends GameWorldManager {

    GameManager(GameView gameView) {
        super(gameView);
    }

    private void gameManagement() {
    }

    @Override
    protected void gameLoop() {
        super.gameLoop();
        gameManagement();
    }
}
