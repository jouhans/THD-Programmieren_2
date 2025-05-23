package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.base.ShiftableGameObject;

/**
 * Creates a new Guided Missile Object using {@link Position} for the Position and using {@link GameView} to display it.
 * <p>
 * This class provides methodes to update the Position and to add the Object to the GameView.
 *
 * @see Position
 * @see GameView
 */
public class GuidedMissile extends CollidingGameObject implements ShiftableGameObject {
    private final PlayerChopper playerChopper;
    private boolean followingPlayer;
    private double directionX;
    private double directionY;

    /**
     * Initializes a new GameObject "EnemyChopper".
     *
     * @param gameView link GameObject to the current GameView
     * @param gamePlayManager link GameObject to the GamePlayManager
     * @param playerChopper link the player chopper to the guided missile
     * @param xCoordinate link a xCoordinate to spawn guided missile there
     */
    public GuidedMissile(GameView gameView, GamePlayManager gamePlayManager, PlayerChopper playerChopper, int xCoordinate) {
        super(gameView, gamePlayManager);
        this.playerChopper = playerChopper;
        followingPlayer = true;
        speedInPixel = 4;
        size = 0.6;
        width = 50;
        height = 25;
        distanceToBackground = 4;
        this.position.updateCoordinates(new Position(xCoordinate, 545));
        targetPosition.updateCoordinates(this.playerChopper.getPosition());
        hitBoxOffsets(0, 0, 0, 0);
        timeAtStart = gameView.gameTimeInMilliseconds();
        calculateRotation();
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof GuidedMissile || other instanceof PlayerChopper) {
            gamePlayManager.destroyGameObject(this);
        }
    }

    @Override
    public void updatePosition() {
        if (followingPlayer) {
            targetPosition.updateCoordinates(this.playerChopper.getPosition());
            position.moveToPosition(targetPosition, speedInPixel);
        } else {
            position.updateCoordinates(
                    position.getX() + directionX * speedInPixel,
                    position.getY() + directionY * speedInPixel
            );
        }
    }

    @Override
    public void updateStatus() {
        if (gameView.gameTimeInMilliseconds() > timeAtStart + 3000) {
            gamePlayManager.destroyGameObject(this);
        }

        if (followingPlayer && timeAtStart + 1000 < gameView.gameTimeInMilliseconds()) {
            followingPlayer = false;
            speedInPixel = 8;

            double dx = playerChopper.getPosition().getX() - position.getX();
            double dy = playerChopper.getPosition().getY() - position.getY();
            double length = Math.sqrt(dx * dx + dy * dy);
            directionX = dx / length;
            directionY = dy / length;

            double farAway = 1000;
            targetPosition.updateCoordinates(position.getX() + directionX * farAway, position.getY() + directionY * farAway);
        }

        if (followingPlayer) {
            calculateRotation();
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("guided_missile.png", position.getX(), position.getY(), size, rotation);
    }

    private void calculateRotation() {
       rotation = calculateAngle(position, playerChopper.getPosition());
    }

    private double calculateAngle(Position position, Position otherPosition) {
        double dx = otherPosition.getX() - position.getX();
        double dy = otherPosition.getY() - position.getY();
        return Math.toDegrees(Math.atan2(dy, dx));
    }

    @Override
    public String toString() {
        return "Guided Missile: " + position;
    }
}

