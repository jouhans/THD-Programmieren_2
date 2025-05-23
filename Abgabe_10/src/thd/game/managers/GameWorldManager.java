package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.movable.*;
import thd.gameobjects.unmovable.*;

import java.util.*;

class GameWorldManager extends GamePlayManager{

    private final Position referencePositionBackground;
    private final Random random;
    private int objectsPerZone;

    protected GameWorldManager(GameView gameView) {
        super(gameView);

        objectsPerZone = 4;
        random = new Random();

        referencePositionBackground = new Position(0, 0);

        score = new Score(gameView, this);
        backgroundGround = new BackgroundGround(gameView, this);

        backgroundAir0 = new BackgroundAir(gameView, this, new Position(referencePositionBackground.getX() + (-3 * 600), referencePositionBackground.getY()));
        backgroundAir1 = new BackgroundAir(gameView, this, new Position(referencePositionBackground.getX() + (-2 * 600), referencePositionBackground.getY()));
        backgroundAir2 = new BackgroundAir(gameView, this, new Position(referencePositionBackground.getX() + (-1 * 600), referencePositionBackground.getY()));
        backgroundAir3 = new BackgroundAir(gameView, this, new Position(referencePositionBackground.getX(), referencePositionBackground.getY()));
        backgroundAir4 = new BackgroundAir(gameView, this, new Position(referencePositionBackground.getX() + 600, referencePositionBackground.getY()));
        backgroundAir5 = new BackgroundAir(gameView, this, new Position(referencePositionBackground.getX() + (2 * 600), referencePositionBackground.getY()));
        backgroundAir6 = new BackgroundAir(gameView, this, new Position(referencePositionBackground.getX() + (3 * 600), referencePositionBackground.getY()));

        overlay = new Overlay(gameView, this);
        minimap = new Minimap(gameView, this);

        playerChopper = new PlayerChopper(gameView, this, backgroundGround);

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
        spawnAllTrucks();
        spawnGameObject(playerChopper);
        spawnGameObject(life);
        spawnGameObject(score);
        spawnGameObject(backgroundGround);
        spawnGameObject(backgroundAir0);
        spawnGameObject(backgroundAir1);
        spawnGameObject(backgroundAir2);
        spawnGameObject(backgroundAir3);
        spawnGameObject(backgroundAir4);
        spawnGameObject(backgroundAir5);
        spawnGameObject(backgroundAir6);
        spawnGameObject(overlay);
        spawnGameObject(minimap);
    }

    private void addActivatableGameObject(GameObject gameObject) {
        activatableGameObjects.add(gameObject);
        addToShiftableGameObjectsIfShiftable(gameObject);
    }

    protected void removeActivatableGameObject(GameObject gameObject) {
        activatableGameObjects.remove(gameObject);
    }

    private void clearListsForPathDecisionsInGameObjects() {}

    @Override
    protected void gameLoop() {
        super.gameLoop();
        activateGameObjects();
        tpBackgroundAirWhileMoving();
        manageGuidedMissile();
    }

    private void spawnAllTrucks() {
        for (int zone = -1; zone <= 1; zone++) {
            int startX = zone * GameView.WIDTH;
            int step = GameView.WIDTH / (objectsPerZone + 1);

            for (int i = 1; i <= objectsPerZone; i++) {
                int x = startX + i * step;
                spawnGameObject(new Truck(gameView, this, x));
            }
        }
    }

    private void manageGuidedMissile() {
        int randomX = random.nextInt(0, 128) * 10;
        if (gameView.timer(3000, 0, this)) {
            spawnGameObject(new GuidedMissile(gameView, this, playerChopper, randomX));
        }
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
                    EnemyJet enemyJet = new EnemyJet(gameView, this);
                    addActivatableGameObject(enemyJet);
                    spawnGameObject(enemyJet);
                    enemyJet.getPosition().updateCoordinates(x, y);
                }
            }
        }
    }
}
