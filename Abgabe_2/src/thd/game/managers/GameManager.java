package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.movable.EnemyChopper;
import thd.gameobjects.movable.Truck;
import thd.gameobjects.unmovable.Life;

class GameManager {
    private final GameView gameView;
    private final Truck truck;
    private final EnemyChopper enemyChopper;
    private final Life life;

    GameManager(GameView gameView) {
        this.gameView = gameView;

        truck = new Truck(gameView);
        enemyChopper = new EnemyChopper(gameView);
        life = new Life(gameView);

    }
    void gameLoop() {
        while (gameView.isVisible()) {
            truck.updatePosition();
            truck.addToCanvas();

            enemyChopper.updatePosition();
            enemyChopper.addToCanvas();

            life.addToCanvas();

            gameView.plotCanvas();
        }
    }
}
