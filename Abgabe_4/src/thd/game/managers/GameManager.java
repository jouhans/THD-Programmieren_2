package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.movable.*;
import thd.gameobjects.unmovable.Life;
import thd.gameobjects.unmovable.Score;

class GameManager extends UserControlledGameObjectPool{

    private final GameObjectManager gameObjectManager;

    GameManager(GameView gameView) {
        super(gameView);

        this.gameObjectManager = new GameObjectManager();

        truck = new Truck(gameView);
        enemyChopper = new EnemyChopper(gameView);
        playerChopper = new PlayerChopper(gameView);
        enemyJet = new EnemyJet(gameView);

        enemyJetBomb = new EnemyJetBomb(gameView);
        playerChopperShot = new PlayerChopperShot(gameView);
        enemyChopperShot = new EnemyChopperShot(gameView);

        life = new Life(gameView);
        score = new Score(gameView);

        gameObjectManager.add(truck);
        gameObjectManager.add(enemyChopper);
        gameObjectManager.add(playerChopper);
        gameObjectManager.add(enemyJet);
        gameObjectManager.add(enemyJetBomb);
        gameObjectManager.add(playerChopperShot);
        gameObjectManager.add(enemyChopperShot);
        gameObjectManager.add(life);
        gameObjectManager.add(score);

    }

    @Override
    protected void gameLoop() {
        super.gameLoop();
        gameObjectManager.gameLoop();
    }
}
