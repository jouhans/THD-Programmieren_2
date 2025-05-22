package thd.game.level;

/**
 * This class is the Top-Class for all Levels.
 * <p>
 * Defines all needed Variables for creating new Levels.
 */
public class Level {
    /**
     * Name of the level.
     */
    public String name;

    /**
     * Difficulty of the level.
     */
    public static Difficulty difficulty = Difficulty.STANDARD;

    /**
     * Number of the level.
     */
    public int number;

    /**
     * The world as a String. E = EnemyChopper, J = EnemyJet
     */
    public String world;

    /**
     * Columns offset of the world String.
     */
    public int worldOffsetColumns;

    /**
     * Lines offset of the world String.
     */
    public int worldOffsetLines;

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
}
