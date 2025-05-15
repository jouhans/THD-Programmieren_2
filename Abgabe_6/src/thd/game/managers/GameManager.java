package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.movable.*;
import thd.gameobjects.unmovable.Ground;
import thd.gameobjects.unmovable.Life;
import thd.gameobjects.unmovable.Score;

class GameManager extends GamePlayManager{

    GameManager(GameView gameView) {
        super(gameView);

        score = new Score(gameView, this);
        ground = new Ground(gameView, this);

        truck = new Truck(gameView, this);
        enemyChopper = new EnemyChopper(gameView, this);
        playerChopper = new PlayerChopper(gameView, this, ground);
        enemyJet = new EnemyJet(gameView, this, ground);

        guidedMissile = new GuidedMissile(gameView, this);
        life = new Life(gameView, this);


        spawnGameObject(truck);
        spawnGameObject(enemyChopper);
        spawnGameObject(playerChopper);
        spawnGameObject(enemyJet);
        spawnGameObject(guidedMissile);
        spawnGameObject(life);
        spawnGameObject(score);
        spawnGameObject(ground);

    }

    private void gameManagement() {
    }

    @Override
    protected void gameLoop() {
        super.gameLoop();
        gameManagement();
    }
}
