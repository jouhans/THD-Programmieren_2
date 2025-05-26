package thd.game.level;

/**
 * Subclass of {@link Level}.
 * <p>
 * Level 3 of Chopper Command.
 *
 * @see Level
 */
public class Level3 extends Level{

    /**
     * Creates Level 3.
     */
    public Level3() {

        name = "Jet Invasion";
        number = 3;

        worldOffsetColumns = 10;
        worldOffsetLines = 3;

        world = """
                                           \s
                                           \s
                                           \s
               J                     J     \s
      J    J           J      J           J\s
   J          J            J               \s
                J        E          J      \s
      J                       J            \s
                                           \s""";

    }
}
