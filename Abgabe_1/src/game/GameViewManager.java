package game;

import java.awt.*;

public class GameViewManager {
    private final GameView gameView;
    private Truck truck;
    private EnemyChopper enemyChopper;
    private Score score;

    public GameViewManager() {
        gameView = new GameView();
        gameView.updateWindowTitle("Chopper Command");
        gameView.updateStatusText("Java Programmierung SS 2025");

        truck = new Truck(gameView);
        enemyChopper = new EnemyChopper(gameView);
        score = new Score(gameView);

        startGameLoop();
    }
    private void startGameLoop() {
        while (gameView.isVisible()) {
            truck.updatePosition();
            truck.addToCanvas();

            enemyChopper.updatePosition();
            enemyChopper.addToCanvas();

            score.addToCanvas();

            gameView.plotCanvas();
        }
    }
}
