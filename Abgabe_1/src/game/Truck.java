package game;

import java.awt.*;

public class Truck {

    private double speedInPixel;
    private double size;
    private double rotation;

    private GameView gameView;
    private Position position;

    public Truck(GameView gameView) {
        this.gameView = gameView;
        this.position = new Position(0, GameView.HEIGHT / 2.0);
        this.speedInPixel = 5;
        this.size = 30.0;
        this.rotation = 0.0;
    }

    public void updatePosition() {
        position.right(speedInPixel);
        rotation += 1;
    }

    public void addToCanvas() {
        gameView.addTextToCanvas("Objekt 1", position.getX(), position.getY(),
                size, true, Color.YELLOW, rotation);
    }

    @Override
    public String toString() {
        return "Truck: " + position;
    }
}
