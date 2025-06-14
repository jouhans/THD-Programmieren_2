package game;

public class Position {

    private double x;
    private double y;

    private GameView gameView;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Position() {
        this(0, 0);
    }
    public Position(Position other) {
        this(other.getX(), other.getY());
    }

    public void left() {
        x -= 1;
    }
    public void left(double pixel) {
        x -= pixel;
    }

    public void right() {
        x += 1;
    }
    public void right(double pixel) {
        x += pixel;
    }

    public void up() {
        y -= 1;
    }
    public void up(double pixel) {
        y -= pixel;
    }

    public void down() {
        y += 1;
    }
    public void down(double pixel) {
        y += pixel;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    void updateCoordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }
    void updateCoordinates(Position other) {
        x = other.getX();
        y = other.getY();
    }

    @Override
    public String toString() {
        return "Position (" + ((int) Math.round(x)) + ", " + ((int) Math.round(y)) + ")";
    }
}
