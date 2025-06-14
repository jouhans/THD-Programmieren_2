package thd.game.managers;

import thd.gameobjects.base.GameObject;

import java.util.LinkedList;
import java.util.List;

class GameObjectManager extends CollisionManager{
    private final List<GameObject> gameObjects;
    private final List<GameObject> gameObjectsToBeAdded;
    private final List<GameObject> gameObjectsToBeRemoved;

    private static final int MAXIMUM_NUMBER_OF_GAME_OBJECTS = 500;

    protected GameObjectManager() {
        gameObjects = new LinkedList<>();
        gameObjectsToBeAdded = new LinkedList<>();
        gameObjectsToBeRemoved = new LinkedList<>();
    }

    void add(GameObject gameObject) {
        gameObjectsToBeAdded.add(gameObject);
    }

    void remove(GameObject gameObject) {
        gameObjectsToBeRemoved.add(gameObject);
    }

    void removeAll() {
        gameObjectsToBeAdded.clear();
        gameObjectsToBeRemoved.addAll(gameObjects);
        gameObjects.clear();
    }

    void gameLoop() {
        updateLists();
            for (GameObject gameObject : gameObjects) {
                gameObject.updateStatus();
                gameObject.updatePosition();
                gameObject.addToCanvas();
            }
        manageCollisions(false);
        }

    private void updateLists() {
        if (gameObjects.size() > MAXIMUM_NUMBER_OF_GAME_OBJECTS) {
            throw new TooManyGameObjectsException("Maximum number of game objects reached: the maximum allowed is " + MAXIMUM_NUMBER_OF_GAME_OBJECTS);
        }
        removeFromGameObjects();
        addToGameObjects();
    }

    private void removeFromGameObjects(){
        for (GameObject gameObject : gameObjectsToBeRemoved) {
            gameObjects.remove(gameObject);
            removeFromCollisionManagement(gameObject);
        }
        gameObjectsToBeRemoved.clear();
    }

    private void addToGameObjects() {
        for (GameObject toAdd : gameObjectsToBeAdded) {
            sortIntoGameObjects(toAdd);
            addToCollisionManagement(toAdd);
        }
        gameObjectsToBeAdded.clear();
    }

    private void sortIntoGameObjects(GameObject toAdd) {
        int indexToSortIn = 0;
        for (GameObject gameObject : gameObjects) {
            if (gameObject.getDistanceToBackground() >= toAdd.getDistanceToBackground()) {
                break;
            }
            indexToSortIn++;
        }
        gameObjects.add(indexToSortIn, toAdd);
    }
}
