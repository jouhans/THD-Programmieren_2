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

        name = "Chopper Backup";
        number = 2;

        worldOffsetColumns = 10;
        worldOffsetLines = 3;

        world = """
                                           \s
                                           \s
                                           \s
                                           \s
                 E     E      E          E \s
          E          J       E      J      \s
                 J      E        J         \s
                                           \s
                                           \s""";

    }
}
