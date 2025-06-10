package thd.game.managers;

import thd.game.level.Level;
import thd.game.utilities.GameView;
import thd.gameobjects.movable.BackgroundAir;
import thd.gameobjects.movable.PlayerChopper;
import thd.gameobjects.unmovable.*;

import java.awt.*;
import java.awt.event.KeyEvent;

class UserControlledGameObjectPool {

    protected final GameView gameView;
    protected Level level;

    protected PlayerChopper playerChopper;

    protected Life life;
    protected Score score;
    protected BackgroundGround backgroundGround;

    protected BackgroundAir backgroundAir0;
    protected BackgroundAir backgroundAir1;
    protected BackgroundAir backgroundAir2;
    protected BackgroundAir backgroundAir3;
    protected BackgroundAir backgroundAir4;
    protected BackgroundAir backgroundAir5;
    protected BackgroundAir backgroundAir6;

    protected Overlay overlay;
    protected Minimap minimap;


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
