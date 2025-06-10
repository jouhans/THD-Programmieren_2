package thd.gameobjects.movable;

import thd.game.level.Level;
import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.base.ShiftableGameObject;

/**
 * Creates a new Guided Missile Object using {@link Position} for the Position
 * and using {@link GameView} to display it.
 * <p>
 * This class provides methods to update the Position and to add the Object to
 * the GameView.
 *
 * @see Position
 * @see GameView
 */
public class GuidedMissile extends CollidingGameObject implements ShiftableGameObject {
    private final PlayerChopper playerChopper;
    private boolean followingPlayer;
    private double directionX;
    private double directionY;
    private MovingState movingState;
    private double animationrotation;
    private double animationXOffset;
    private double animationYOffset;
    private final double animationDistance; // Abstand der Animation zur Rakete
    private final double animationXCorrection; // Zusätzliche X-Korrektur

    private enum MovingState {
        MOVE_STATE1, MOVE_STATE2, MOVE_STATE3, MOVE_STATE4
    }

    /**
     * Initializes a new GameObject "EnemyChopper".
     *
     * @param gameView        link GameObject to the current GameView
     * @param gamePlayManager link GameObject to the GamePlayManager
     * @param playerChopper   link the player chopper to the guided missile
     * @param xCoordinate     link a xCoordinate to spawn guided missile there
     */
    public GuidedMissile(GameView gameView, GamePlayManager gamePlayManager, PlayerChopper playerChopper,
            int xCoordinate) {
        super(gameView, gamePlayManager);
        this.playerChopper = playerChopper;
        followingPlayer = true;
        switch (Level.difficulty) {
            case STANDARD -> speedInPixel = 4;
            case EASY -> speedInPixel = 2;
        }
        size = 0.6;
        width = 50;
        height = 25;
        animationDistance = 35; // Standard-Abstand der Animation zur Rakete
        animationXCorrection = 21; // Zusätzliche X-Korrektur für perfekte Positionierung (erhöht von 10 auf 20)
        distanceToBackground = 4;
        this.position.updateCoordinates(new Position(xCoordinate, 545));
        targetPosition.updateCoordinates(this.playerChopper.getPosition());
        hitBoxOffsets(0, 0, 0, 0);
        timeAtStart = gameView.gameTimeInMilliseconds();
        calculateRotation(); // Dies berechnet auch die animationOffsets
        movingState = MovingState.MOVE_STATE1;
        gameView.playSound("guided_missile.wav", false);
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof GuidedMissile || other instanceof PlayerChopper || other instanceof PlayerChopperShot) {
            gamePlayManager.destroyGameObject(this);
            gameView.playSound("small_explosion.wav", false);
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
                    position.getY() + directionY * speedInPixel);
        }
    }

    @Override
    public void updateStatus() {
        if (gameView.gameTimeInMilliseconds() > timeAtStart + 3000) {
            gamePlayManager.destroyGameObject(this);
            gamePlayManager.spawnGameObject(new Explosion(gameView, gamePlayManager, position));
        }

        if (followingPlayer && timeAtStart + 1000 < gameView.gameTimeInMilliseconds()) {
            followingPlayer = false;
            speedInPixel += 2;

            double dx = playerChopper.getPosition().getX() - position.getX();
            double dy = playerChopper.getPosition().getY() - position.getY();
            double length = Math.sqrt(dx * dx + dy * dy);
            directionX = dx / length;
            directionY = dy / length;

            double farAway = 1000;
            targetPosition.updateCoordinates(position.getX() + directionX * farAway,
                    position.getY() + directionY * farAway);
        }

        if (followingPlayer) {
            calculateRotation();
            calculateAnimationOffsets();
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("guided_missile.png", position.getX(), position.getY(), size, rotation);
        switch (movingState) {
            case MOVE_STATE1 -> {
                gameView.addImageToCanvas("guidedmissile_burn1.png", position.getX() + animationXOffset,
                        position.getY() + animationYOffset, 2, animationrotation);
                if (gameView.timer(80, 0, this)) {
                    switchmovingState();
                }
            }
            case MOVE_STATE2 -> {
                gameView.addImageToCanvas("guidedmissile_burn2.png", position.getX() + animationXOffset,
                        position.getY() + animationYOffset, 2, animationrotation);
                if (gameView.timer(80, 0, this)) {
                    switchmovingState();
                }
            }
            case MOVE_STATE3 -> {
                gameView.addImageToCanvas("guidedmissile_burn3.png", position.getX() + animationXOffset,
                        position.getY() + animationYOffset, 2, animationrotation);
                if (gameView.timer(80, 0, this)) {
                    switchmovingState();
                }
            }
            case MOVE_STATE4 -> {
                gameView.addImageToCanvas("guidedmissile_burn4.png", position.getX() + animationXOffset,
                        position.getY() + animationYOffset, 2, animationrotation);
                if (gameView.timer(80, 0, this)) {
                    switchmovingState();
                }
            }
        }
    }



    private void switchmovingState() {
        int nextState = (movingState.ordinal() + 1) % MovingState.values().length;
        movingState = MovingState.values()[nextState];
    }

    private void calculateRotation() {
        rotation = calculateAngle(position, playerChopper.getPosition());
        animationrotation = rotation + 90;
        calculateAnimationOffsets();
    }

    private void calculateAnimationOffsets() {
        double angleInRadians = Math.toRadians(rotation + 180);

        animationXOffset = Math.cos(angleInRadians) * animationDistance + animationXCorrection;
        animationYOffset = Math.sin(angleInRadians) * animationDistance;
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
