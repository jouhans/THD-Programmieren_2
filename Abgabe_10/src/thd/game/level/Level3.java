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

        name = "Level 3";
        number = 3;

        worldOffsetColumns = 20;
        worldOffsetLines = 3;

        world = """
                                           \s
                                           \s
                                           \s
                           J               \s
                 J     J      J            \s
                    E      E               \s
                E        E          E      \s
                                           \s
                                           \s""";

    }
}
