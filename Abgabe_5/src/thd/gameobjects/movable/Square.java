package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

import java.awt.*;


public class Square extends GameObject {

    /**
     * Initializes a new GameObject "EnemyChopper".
     *
     * @param gameView link GameObject to the current GameView
     * @param gamePlayManager link GameObject to the GamePlayManager
     */
    public Square(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        speedInPixel = 5;
        size = 1.0;
        width = 30;
        height = 30;
        position.updateCoordinates(100, 100);
    }

    @Override
    public void updatePosition() {
        position.right(speedInPixel);
    }

    @Override
    public void updateStatus() {
        if (position.getX() > width + 1280 || position.getX() < 0 - width) {
            gamePlayManager.destroyGameObject(this);
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addRectangleToCanvas(position.getX(), position.getY(), width, height, 3.0, false, Color.RED);
    }

    @Override
    public String toString() {
        return "Enemy Chopper: " + position;
    }
}

