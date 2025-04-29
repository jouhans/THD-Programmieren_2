package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.movable.*;
import thd.gameobjects.unmovable.Life;
import thd.gameobjects.unmovable.Score;

class GameManager extends GamePlayManager{

    GameManager(GameView gameView) {
        super(gameView);

        truck = new Truck(gameView, this);
        enemyChopper = new EnemyChopper(gameView, this);
        playerChopper = new PlayerChopper(gameView, this);
        enemyJet = new EnemyJet(gameView, this);

        guidedMissile = new GuidedMissile(gameView, this);

        life = new Life(gameView, this);
        score = new Score(gameView, this);

        spawnGameObject(truck);
        spawnGameObject(enemyChopper);
        spawnGameObject(playerChopper);
        spawnGameObject(enemyJet);
        spawnGameObject(guidedMissile);
        spawnGameObject(life);
        spawnGameObject(score);

    }

    private void gameManagement() {
    }

    @Override
    protected void gameLoop() {
        super.gameLoop();
        gameManagement();
    }
}
