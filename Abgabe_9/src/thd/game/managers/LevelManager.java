package thd.game.managers;

import thd.game.level.*;
import thd.game.utilities.GameView;

import java.util.List;

class LevelManager extends GameWorldManager{

    private List<Level> levels;
    private static final int LIVES = 3;

    protected LevelManager(GameView gameView) {
        super(gameView);
    }


    @Override
    protected void initializeLevel() {
        super.initializeLevel();
        initializeGameObjects();
    }

    protected void initializeGame() {
        levels = List.of(new Level1(), new Level2(), new Level3());
        level = levels.get(0);
        lives = LIVES;
        points = 0;
    }

    protected boolean hasNextLevel() {
        return level !=levels.get(levels.size() - 1);
    }

    protected void switchToNextLevel() {
        if (hasNextLevel()) {
            level = levels.get(levels.indexOf(level) + 1);
        } else {
            throw new NoMoreLevelsAvailableException("There are no more Levels. Error generated in LevelManager");
        }
    }

    private void initializeGameObjects() {
        // Die Methode initializeGameObjects() soll in Zukunft dazu genutzt werden, um Spielelemente an ein
        // neues Level anzupassen, z.B.
        // Anpassungen für das Level am Hintergrund machen.
        // Die Lebensanzeige aktualisieren.
        // Den Punktestand aus dem vorherigen Level übernehmen.
        // Einen Countdown neu starten.
    }

}
