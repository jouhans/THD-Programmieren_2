package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.ActivatableGameObject;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.base.ShiftableGameObject;
import thd.gameobjects.unmovable.BackgroundGround;

import java.util.Random;

/**
 * Creates a new enemy helicopter Object using {@link Position} for the Position and using {@link GameView} to display it.
 * <p>
 * This class provides methodes to update the Position and to add the Object to the GameView.
 *
 * @see Position
 * @see GameView
 */
public class EnemyJet extends CollidingGameObject implements ShiftableGameObject, ActivatableGameObject<EnemyJet> {
    private int shotDurationInMilliseconds;
    private final Random random;


    /**
     * Initializes a new GameObject "EnemyChopper".
     *
     * @param gameView link GameObject to the current GameView
     * @param gamePlayManager link GameObject to the GamePlayManager
     * @param backgroundGround link backgroundGround to the EnemyJet
     */
    public EnemyJet(GameView gameView, GamePlayManager gamePlayManager, BackgroundGround backgroundGround) {
        super(gameView, gamePlayManager);
        random = new Random();
        speedInPixel = 4;
        size = 2.0;
        width = 80;
        height = 30;
        distanceToBackground = 4;
        position.updateCoordinates(new Position(300.0 + random.nextDouble(350), 100.0 + random.nextDouble(50)));
        shotDurationInMilliseconds = gameView.gameTimeInMilliseconds();
        hitBoxOffsets(0, 0, 0, 0);
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof PlayerChopperShot || other instanceof PlayerChopper) {
            gamePlayManager.destroyGameObject(this);
            gamePlayManager.addPoints(GamePlayManager.ENEMY_JET_POINTS);
        }
    }

    @Override
    public void updatePosition() {
        if (gameView.timer(3000, 3000, this)) {
            position.right(speedInPixel);
        } else {
            position.left(speedInPixel);
        }
    }

    @Override
    public void updateStatus() {
        int randomNumber = random.nextInt(3) + 3;
        if (shotDurationInMilliseconds + (randomNumber * 1000) <= gameView.gameTimeInMilliseconds()) {
            gamePlayManager.spawnGameObject(new EnemyJetBomb(gameView, gamePlayManager, position));
            shotDurationInMilliseconds = gameView.gameTimeInMilliseconds();
        }
    }

    private void drop() {
        gamePlayManager.spawnGameObject(new EnemyJetBomb(gameView, gamePlayManager, position));
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("enemyjet.png", position.getX(), position.getY(), size, rotation);
    }

    @Override
    public String toString() {
        return "Enemy Jet: " + position;
    }

    @Override
    public boolean tryToActivate(EnemyJet info) {
        EnemyJet tryToActivate = (EnemyJet) info;

        return !(tryToActivate.position.getX() < 0) && !(tryToActivate.position.getX() > 1280) && !(tryToActivate.position.getY() < 0) && !(tryToActivate.position.getY() > 720);
    }
}

