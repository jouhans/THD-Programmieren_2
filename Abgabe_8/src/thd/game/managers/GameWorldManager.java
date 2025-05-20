package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.movable.*;
import thd.gameobjects.unmovable.BackgroundAir;
import thd.gameobjects.unmovable.BackgroundGround;
import thd.gameobjects.unmovable.Life;
import thd.gameobjects.unmovable.Score;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

class GameWorldManager extends GamePlayManager{

    private final List<GameObject> activatableGameObjects;
    private final Position referencePositionBackground;

    protected GameWorldManager(GameView gameView) {
        super(gameView);

        referencePositionBackground = new Position(0, 0);

        activatableGameObjects = new LinkedList<>();

        score = new Score(gameView, this);
        backgroundGround = new BackgroundGround(gameView, this);

        backgroundAir1 = new BackgroundAir(gameView, this, new Position(referencePositionBackground.getX() + (-3 * 600), referencePositionBackground.getY()));
        backgroundAir2 = new BackgroundAir(gameView, this, new Position(referencePositionBackground.getX() + (-2 * 600), referencePositionBackground.getY()));
        backgroundAir3 = new BackgroundAir(gameView, this, new Position(referencePositionBackground.getX() + (-1 * 600), referencePositionBackground.getY()));
        backgroundAir4 = new BackgroundAir(gameView, this, new Position(referencePositionBackground.getX(), referencePositionBackground.getY()));
        backgroundAir5 = new BackgroundAir(gameView, this, new Position(referencePositionBackground.getX() + 600, referencePositionBackground.getY()));
        backgroundAir6 = new BackgroundAir(gameView, this, new Position(referencePositionBackground.getX() + (2 * 600), referencePositionBackground.getY()));
        backgroundAir7 = new BackgroundAir(gameView, this, new Position(referencePositionBackground.getX() + (3 * 600), referencePositionBackground.getY()));


        truck = new Truck(gameView, this);
        playerChopper = new PlayerChopper(gameView, this, backgroundGround);

        guidedMissile = new GuidedMissile(gameView, this);
        life = new Life(gameView, this);

    }

    protected void initializeLevel() {
        activatableGameObjects.clear();
        destroyAllGameObjects();
        spawnGameObjects();
        spawnGameObjectsFromWorldString();
        clearListsForPathDecisionsInGameObjects();
    }

    private void spawnGameObjects() {
        spawnGameObject(truck);
        spawnGameObject(playerChopper);
        spawnGameObject(guidedMissile);
        spawnGameObject(life);
        spawnGameObject(score);
        spawnGameObject(backgroundGround);
        spawnGameObject(backgroundAir1);
        spawnGameObject(backgroundAir2);
        spawnGameObject(backgroundAir3);
        spawnGameObject(backgroundAir4);
        spawnGameObject(backgroundAir5);
        spawnGameObject(backgroundAir6);
        spawnGameObject(backgroundAir7);

    }

    private void addActivatableGameObject(GameObject gameObject) {
        activatableGameObjects.add(gameObject);
        addToShiftableGameObjectsIfShiftable(gameObject);
    }
    private void clearListsForPathDecisionsInGameObjects() {

    }

    @Override
    protected void gameLoop() {
        super.gameLoop();
        activateGameObjects();
        tpBackgroundAirWhileMoving();
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
        String[] lines = level.world.split("\\R");

        for (int line = 0; line < lines.length; line++) {
            for (int column = 0; column < lines[line].length(); column++) {
                double x = (column - level.worldOffsetColumns) * 100;
                double y = (line - level.worldOffsetLines) * 100;
                char character = lines[line].charAt(column);
                if (character == 'E') {
                    EnemyChopper enemyChopper = new EnemyChopper(gameView, this);
                    addActivatableGameObject(enemyChopper);
                    spawnGameObject(enemyChopper);
                    enemyChopper.getPosition().updateCoordinates(x, y);
                } else if (character == 'J') {
                    EnemyJet enemyJet = new EnemyJet(gameView, this, backgroundGround);
                    addActivatableGameObject(enemyJet);
                    spawnGameObject(enemyJet);
                    enemyJet.getPosition().updateCoordinates(x, y);
                }
            }
        }

    }
}
