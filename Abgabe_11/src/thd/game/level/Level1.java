package thd.game.level;

/**
 * Subclass of {@link Level}.
 * <p>
 * Level 1 of Chopper Command.
 *
 * @see Level
 */
public class Level1 extends Level{

    /**
     * Creates Level 1.
     */
    public Level1() {

        name = "Trouble in the desert";
        number = 1;

        worldOffsetColumns = 10;
        worldOffsetLines = 3;

        world = """
                                           \s
                                           \s
                                           \s
                                           \s
            J        J       J             \s
       J     E             J            E  \s
                 E     E      E            \s
                                           \s
                                           \s""";


    }
}
