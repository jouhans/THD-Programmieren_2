package game;

import java.awt.*;

public class Score {

    private GameView gameView;
    private Position position;

    private double size;
    private double rotation;

    private double width;
    private double height;

    public Score(GameView gameView) {
        this.gameView = gameView;
        this.size = 30.0;
        this.rotation = 0.0;
        this.width = 150;
        this.height = 33;
        this.position = new Position(GameView.WIDTH - width, -8);
    }

    public void addToCanvas() {
        gameView.addTextToCanvas("Objekt 3", position.getX(), position.getY(),
                size, true, Color.YELLOW, rotation);
    }

    @Override
    public String toString() {
        return "Score: " + position;
    }
}
