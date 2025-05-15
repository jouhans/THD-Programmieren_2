package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.movable.*;
import thd.gameobjects.unmovable.Ground;
import thd.gameobjects.unmovable.Life;
import thd.gameobjects.unmovable.Score;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

class GameWorldManager extends GamePlayManager{

    private final String world;
    private final int worldOffsetColumns;
    private final int worldOffsetLines;
    private final List<GameObject> activatableGameObjects;

    protected GameWorldManager(GameView gameView) {
        super(gameView);

        activatableGameObjects = new LinkedList<>();

        worldOffsetColumns = 20;
        worldOffsetLines = 3;

        //E = EnemyChopper
        //J = EnemyJet
        /*
        world = """
           J       J   \s
                 J     \s
             E      E  \s
                       \s
                       \s
                       \s
                       \s""";
         */
        world = """
                                           \s
                                           \s
        J            J       J             \s
             E             J               \s
                 E     E      E            \s
                                           \s
                                           \s
                                           \s
                                           \s""";

        score = new Score(gameView, this);
        ground = new Ground(gameView, this);

        truck = new Truck(gameView, this);
        playerChopper = new PlayerChopper(gameView, this, ground);

        guidedMissile = new GuidedMissile(gameView, this);
        life = new Life(gameView, this);

        spawnGameObjects();
        spawnGameObjectsFromWorldString();
    }

    private void spawnGameObjects() {
        spawnGameObject(truck);
        spawnGameObject(playerChopper);
        spawnGameObject(guidedMissile);
        spawnGameObject(life);
        spawnGameObject(score);
        spawnGameObject(ground);
    }

    private void addActivatableGameObject(GameObject gameObject) {
        activatableGameObjects.add(gameObject);
        addToShiftableGameObjectsIfShiftable(gameObject);
    }

    @Override
    protected void gameLoop() {
        super.gameLoop();
        activateGameObjects();
    }

    private void activateGameObjects() {
        ListIterator<GameObject> iterator = activatableGameObjects.listIterator();

        while (iterator.hasNext()) {
            GameObject gameObject = iterator.next();
            if (gameObject instanceof EnemyChopper enemyChopper) {
                if (enemyChopper.tryToActivate(enemyChopper)) {
                    spawnGameObject(enemyChopper);
                    iterator.remove();
                }
            } else if (gameObject instanceof EnemyJet enemyJet) {
                if (enemyJet.tryToActivate(enemyJet)) {
                    spawnGameObject(enemyJet);
                    iterator.remove();
                }
            }
        }

    }


    private void spawnGameObjectsFromWorldString() {
        String[] lines = world.split("\\R");

        for (int line = 0; line < lines.length; line++) {
            for (int column = 0; column < lines[line].length(); column++) {
                double x = (column - worldOffsetColumns) * 100;
                double y = (line - worldOffsetLines) * 100;
                char character = lines[line].charAt(column);
                if (character == 'E') {
                    EnemyChopper enemyChopper = new EnemyChopper(gameView, this);
                    addActivatableGameObject(enemyChopper);
                    spawnGameObject(enemyChopper);
                    enemyChopper.getPosition().updateCoordinates(x, y);
                } else if (character == 'J') {
                    EnemyJet enemyJet = new EnemyJet(gameView, this, ground);
                    addActivatableGameObject(enemyJet);
                    spawnGameObject(enemyJet);
                    enemyJet.getPosition().updateCoordinates(x, y);
                }
            }
        }

    }
}
