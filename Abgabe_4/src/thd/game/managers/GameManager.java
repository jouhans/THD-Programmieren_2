package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.movable.EnemyChopper;
import thd.gameobjects.movable.EnemyJet;
import thd.gameobjects.movable.PlayerChopper;
import thd.gameobjects.movable.Truck;
import thd.gameobjects.unmovable.Life;

class GameManager extends UserControlledGameObjectPool{

    GameManager(GameView gameView) {
        super(gameView);

        truck = new Truck(gameView);
        enemyChopper = new EnemyChopper(gameView);
        life = new Life(gameView);
        playerChopper = new PlayerChopper(gameView);
        enemyJet = new EnemyJet(gameView);
    }

    @Override
    protected void gameLoop() {
        super.gameLoop();
            truck.updatePosition();
            truck.addToCanvas();

            enemyChopper.updatePosition();
            enemyChopper.addToCanvas();

            enemyJet.updatePosition();
            enemyJet.addToCanvas();

            playerChopper.updateStatus();
            playerChopper.updatePosition();
            playerChopper.addToCanvas();

            life.addToCanvas();
    }
}
