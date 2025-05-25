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

        switch (difficulty) {
            case STANDARD -> {}
            case EASY -> {}
            case HARD -> {}
            case IMPOSSIBLE -> {}

        }


        worldOffsetColumns = 20;
        worldOffsetLines = 3;

        world = """
                                           \s
                                           \s
                                           \s
                                           \s
            J        J       J             \s
             E             J               \s
                 E     E      E            \s
                                           \s
                                           \s""";

    }
}
