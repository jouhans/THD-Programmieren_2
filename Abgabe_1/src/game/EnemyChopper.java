package game;

import java.awt.*;

public class EnemyChopper {

    private GameView gameView;
    private Position position;

    private double speedInPixel;
    private double size;
    private double rotation;

    private double width;
    private double height;

    public EnemyChopper(GameView gameView) {
        this.gameView = gameView;
        this.position = new Position(1100, 650);
        this.speedInPixel = 2;
        this.size = 30.0;
        this.rotation = 0.0;
        this.width = 150;
        this.height = 33;
    }

    public void updatePosition() {
        position.left(speedInPixel);
    }

    public void addToCanvas() {
        gameView.addRectangleToCanvas(position.getX(), position.getY(), width, height, 0.0, true, Color.GREEN);
        gameView.addRectangleToCanvas(position.getX(), position.getY(), width, height, 5.0, false, Color.WHITE);
        gameView.addTextToCanvas("Objekt 2", position.getX(), position.getY(),
                size, true, Color.BLUE, rotation);
    }

    @Override
    public String toString() {
        return "Enemy Chopper: " + position;
    }
}

