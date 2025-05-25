package thd.game.utilities;

import thd.gameobjects.base.GameObject;

import java.util.LinkedList;

/**
 * Creates a SortedGameObjectsList and is a subclass of LinkedList.
 */
public class SortedGameObjectsList extends LinkedList<GameObject> {

    @Override
    public boolean add(GameObject gameObject) {
        int indexToSortIn = 0;
        for (GameObject otherGameObject : this) {
            if (otherGameObject.getDistanceToBackground() >= gameObject.getDistanceToBackground()) {
                break;
            }
            indexToSortIn++;
        }
        this.add(indexToSortIn, gameObject);
        return true;
    }
}
