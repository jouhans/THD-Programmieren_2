package thd.gameobjects.base;

/**
 * Represents the main controllable character in the game.
 * <p>
 * Classes with this interface have to define a shooting action.
 */
public interface MainCharacter {

    /**
     * Let the Main character shoot.
     */
    void shoot();
}
