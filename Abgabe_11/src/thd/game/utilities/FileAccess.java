package thd.game.utilities;

import thd.game.level.Difficulty;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;


public class FileAccess {

    public static final String WICHTEL_GAME_FILE = "";

    public static void writeDifficultyToDisc(Difficulty difficulty){}
    public static Difficulty readDifficultyFromDisc() throws IOException {
        List<String> lines = Files.readAllLines(Path.of(WICHTEL_GAME_FILE)), StandardCharsets.UTF_8;
    }
}
