package thd.game.managers;

import thd.gameobjects.base.GameObject;

import java.util.LinkedList;
import java.util.List;

class GameObjectManager {
    private final List<GameObject> gameObjects;
    private final List<GameObject> gameObjectsToBeAdded;
    private final List<GameObject> gameObjectsToBeRemoved;

    protected GameObjectManager() {
        gameObjects = new LinkedList<>();
        gameObjectsToBeAdded = new LinkedList<>();
        gameObjectsToBeRemoved = new LinkedList<>();
    }

    protected void add(GameObject gameObject) {
        gameObjectsToBeAdded.add(gameObject);
    }

    protected void remove(GameObject gameObject) {
        gameObjectsToBeRemoved.add(gameObject);
    }

    protected void removeAll() {
        gameObjectsToBeAdded.clear();
        gameObjectsToBeRemoved.addAll(gameObjects);
        gameObjects.clear();
    }

    void gameLoop() {
        updateLists();
        for (GameObject gameObject: gameObjects) {
            gameObject.updateStatus();
            gameObject.updatePosition();
            gameObject.addToCanvas();
        }
    }

    private void updateLists() {
        removeFromGameObjects();
        addToGameObjects();
    }

    private void removeFromGameObjects(){
        gameObjects.removeAll(gameObjectsToBeRemoved);
        gameObjectsToBeRemoved.clear();
    }

    private void addToGameObjects() {
        gameObjects.addAll(gameObjectsToBeAdded);
        gameObjectsToBeAdded.clear();
    }
}
