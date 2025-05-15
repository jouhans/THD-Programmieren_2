package thd.game.level;

/**
 * Subclass of {@link Level}.
 * <p>
 * Level 2 of Chopper Command.
 *
 * @see Level
 */
public class Level2 extends Level{

    /**
     * Creates Level 2.
     */
    public Level2() {

        name = "Level 2";
        number = 2;

        worldOffsetColumns = 20;
        worldOffsetLines = 3;

        world = """
                                           \s
                                           \s
                     J       J             \s
             E                             \s
                 E     E      J            \s
                     J       E      J      \s
                        E                  \s
                                           \s
                                           \s""";

    }
}
