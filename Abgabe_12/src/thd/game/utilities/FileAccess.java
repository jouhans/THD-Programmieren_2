package thd.game.utilities;

import thd.game.level.Difficulty;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Utility class for file access in the game.
 * Enables saving and loading the difficulty level to a savegame file.
 * The file is stored in the user directory under the subdirectory "game".
 */
public class FileAccess {

    private static final Path SAVEGAME_DIRECTORY = Paths.get(System.getProperty("user.home")).resolve("game");
    private static final String SAVEGAME_FILENAME = "johannes_riedel_game.txt";

    /**
     * The complete path to the savegame file.
     * Composed of SAVEGAME_DIRECTORY and SAVEGAME_FILENAME.
     */
    public static final Path SAVEGAME_FILE = SAVEGAME_DIRECTORY.resolve(SAVEGAME_FILENAME);

    /**
     * Writes the given difficulty level to a file on the hard disk.
     *
     * @param difficulty The difficulty level to be saved
     */
    public static void writeDifficultyToDisc(Difficulty difficulty) {
        try {
            if (!Files.exists(SAVEGAME_DIRECTORY)) {
                Files.createDirectories(SAVEGAME_DIRECTORY);
            }

            Files.writeString(SAVEGAME_FILE, difficulty.name());

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Reads the difficulty level from a file on the hard disk.
     *
     * @return The loaded difficulty level or STANDARD as fallback
     */
    public static Difficulty readDifficultyFromDisc() {
        try {
            String difficultyString = Files.readString(SAVEGAME_FILE);
            String trimmedString = difficultyString.trim();

            for (Difficulty diff : Difficulty.values()) {
                if (diff.name().equals(trimmedString)) {
                    return diff;
                }
            }
            return Difficulty.STANDARD;

        } catch (IOException e) {
            return Difficulty.STANDARD;
        }
    }
}
