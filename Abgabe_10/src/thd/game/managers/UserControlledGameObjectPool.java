package thd.game.managers;

import thd.game.level.Level;
import thd.game.utilities.GameView;
import thd.gameobjects.movable.GuidedMissile;
import thd.gameobjects.movable.PlayerChopper;
import thd.gameobjects.movable.Truck;
import thd.gameobjects.unmovable.BackgroundAir;
import thd.gameobjects.unmovable.BackgroundGround;
import thd.gameobjects.unmovable.Life;
import thd.gameobjects.unmovable.Score;

import java.awt.*;
import java.awt.event.KeyEvent;

class UserControlledGameObjectPool {

    protected final GameView gameView;
    protected Level level;

    protected Truck truck;
    protected PlayerChopper playerChopper;

    protected GuidedMissile guidedMissile;

    protected Life life;
    protected Score score;
    protected BackgroundGround backgroundGround;

    protected BackgroundAir backgroundAir1;
    protected BackgroundAir backgroundAir2;
    protected BackgroundAir backgroundAir3;
    protected BackgroundAir backgroundAir4;
    protected BackgroundAir backgroundAir5;
    protected BackgroundAir backgroundAir6;
    protected BackgroundAir backgroundAir7;


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
        if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
            playerChopper.left();
        } else if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
            playerChopper.right();
        } else if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
            playerChopper.up();
        } else if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
            playerChopper.down();
        } else if (keyCode == KeyEvent.VK_SPACE) {
            playerChopper.shoot();
        }
    }
}
