package thd.game.level;

/**
 * This enum lists all Difficulty which can be used by all Levels.
 */
public enum Difficulty {
    /**
     * Standard difficulty.
     */
    STANDARD("Standard"),
    /**
     * Easy difficulty.
     */
    EASY("Einfach");

    /**
     * The name of the difficulty level, used for display and selection.
     */
    public final String name;

    /**
     * Constructs a new Difficulty enum constant with the given name.
     *
     * @param name The name of the difficulty level.
     */
    Difficulty(String name) {
        this.name = name;
    }

    /**
     * Returns the Difficulty enum constant that matches the given name.
     *
     * @param name The name to match (e.g., "Einfach", "Standard").
     * @return The corresponding Difficulty.
     * @throws IllegalArgumentException if no Difficulty matches the given name.
     */
    public static Difficulty fromName(String name) {
        for (Difficulty difficulty : values()) {
            if (difficulty.name.equalsIgnoreCase(name)) {
                return difficulty;
            }
        }
        throw new IllegalArgumentException("No difficulty found for name: " + name);
    }
}
