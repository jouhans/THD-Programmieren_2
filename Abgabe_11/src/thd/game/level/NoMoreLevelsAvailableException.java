package thd.game.level;

/**
 * Throw this Exception when there are no further levels left.
 */
public class NoMoreLevelsAvailableException extends RuntimeException {
    /**
     * Creates a new NoMoreLevelsAvailable Exception, if no further levels are left.
     *
     * @param message Exception Message
     */
    public NoMoreLevelsAvailableException(String message) {
        super(message);
    }
}
