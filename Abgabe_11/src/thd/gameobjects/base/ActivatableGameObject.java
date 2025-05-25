package thd.gameobjects.base;

/**
 * This interface is used for game objects that can be activatable only under certain conditions.
 *
 * @param <T> type of the game object
 */
public interface ActivatableGameObject<T> {

    /**
     * Check if the game object is activatable or not.
     *
     * @param info required info for activation
     * @return true only when the game object is activatable
     */
    boolean tryToActivate(T info);

    /**
     * Despawn the game Object.
     */
    void deactivate();

    /**
     * spawn the game Object.
     */
    void activate();

    /**
     * Checks if the gameObject is active.
     *
     * @return if the object is active.
     */
    boolean isActive();
}
