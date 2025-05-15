package thd.game.level;

/**
 * This class is the Top-Class for all Levels.
 * <p>
 * Defines all needed Variables for creating new Levels.
 */
public class Level {
    /**
     * Name of the Level.
     */
    public String name;

    /**
     * Number of the Level.
     */
    public int number;

    /**
     * The World as a String. E = EnemyChopper, J = EnemyJet
     */
    public String world;

    /**
     * Columns Offset of the World String.
     */
    public int worldOffsetColumns;

    /**
     * Lines Offset of the world String.
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
