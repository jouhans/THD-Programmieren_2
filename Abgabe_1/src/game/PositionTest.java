package game;

public class PositionTest {
    public static void main(String[] args) {
        // First Position
        Position position1 = new Position(100, 110);
        System.out.println(position1);

        // Second Position
        var position2 = new Position(200, 300);
        System.out.println(position2);

        position2.up();
        position2.right();
        System.out.println(position2);
    }
}
