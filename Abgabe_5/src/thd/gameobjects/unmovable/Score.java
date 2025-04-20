package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;

import java.awt.*;

/**
 * Creates a new Score Object using {@link Position} for the Position and using {@link GameView} to display it.
 * <p>
 * This class provides methodes to update the Position and to add the Object to the GameView.
 *
 * @see Position
 * @see GameView
 */
public class Score extends GameObject {

    private final int score;

    /**
     * Initializes a new GameObject "Score".
     *
     * @param gameView link GameObject to the current GameView
     * @param gamePlayManager link GameObject to the GamePlayManager
     */
    public Score(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        size = 30.0;
        rotation = 0.0;
        width = 150;
        height = 33;
        position.updateCoordinates(new Position(640 - width/2, 0));
        score = 0;
    }

    @Override
    public void addToCanvas() {
        gameView.addTextToCanvas("" + score, position.getX(), position.getY(), size, true, Color.WHITE, rotation, "pressstart2pregular.ttf");
    }

    @Override
    public String toString() {
        return "Score: " + position;
    }
}
