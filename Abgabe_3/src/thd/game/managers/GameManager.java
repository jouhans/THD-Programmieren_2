package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.movable.EnemyChopper;
import thd.gameobjects.movable.FollowerBall;
import thd.gameobjects.movable.RandomBall;
import thd.gameobjects.movable.Truck;
import thd.gameobjects.unmovable.Life;

class GameManager {
    private final GameView gameView;
    private final Truck truck;
    private final EnemyChopper enemyChopper;
    private final Life life;
    private final RandomBall randomBall;
    private final FollowerBall followerBall;

    GameManager(GameView gameView) {
        this.gameView = gameView;

        this.truck = new Truck(gameView);
        this.enemyChopper = new EnemyChopper(gameView);
        this.life = new Life(gameView);
        this.randomBall = new RandomBall(gameView);
        this.followerBall = new FollowerBall(gameView, randomBall);

    }
    void gameLoop() {
            truck.updatePosition();
            truck.addToCanvas();

            enemyChopper.updatePosition();
            enemyChopper.addToCanvas();

            life.addToCanvas();

            randomBall.updatePosition();
            randomBall.addToCanvas();

            followerBall.updatePosition();
            followerBall.addToCanvas();
    }
}
