package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.movable.*;
import thd.gameobjects.unmovable.Life;
import thd.gameobjects.unmovable.Score;

import java.awt.*;
import java.awt.event.KeyEvent;

class UserControlledGameObjectPool {

    protected final GameView gameView;

    protected Truck truck;
    protected EnemyChopper enemyChopper;
    protected PlayerChopper playerChopper;
    protected EnemyJet enemyJet;

    protected EnemyJetBomb enemyJetBomb;
    protected GuidedMissile guidedMissile;
    protected EnemyChopperShot enemyChopperShot;

    protected Life life;
    protected Score score;

    UserControlledGameObjectPool(GameView gameView) {
        this.gameView = gameView;
    }

    protected void gameLoop() {
        Integer[] pressedKeys = gameView.keyCodesOfCurrentlyPressedKeys();
        for (int keyCode : pressedKeys) {
            processKeyCode(keyCode);
            if (keyCode == KeyEvent.VK_A) {
                gameView.addTextToCanvas("Taste A gedrückt", 0, 0, 18, true, Color.WHITE, 0, "pressstart2pregular.ttf");
            }
        }
    }

    private void processKeyCode(int keyCode) {
        if (keyCode == KeyEvent.VK_A) {
            playerChopper.left();
        } else if (keyCode == KeyEvent.VK_D) {
            playerChopper.right();
        } else if (keyCode == KeyEvent.VK_W) {
            playerChopper.up();
        } else if (keyCode == KeyEvent.VK_S) {
            playerChopper.down();
        } else if (keyCode == KeyEvent.VK_SPACE) {
            playerChopper.shoot();
        }
    }
}
